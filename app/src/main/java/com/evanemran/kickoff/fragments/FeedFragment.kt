package com.evanemran.kickoff.fragments

import android.app.ProgressDialog
import android.content.ContentResolver
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.MimeTypeMap
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.evanemran.kickoff.R
import com.evanemran.kickoff.adapters.PostsAdapter
import com.evanemran.kickoff.database.FirebaseDbConstants.TABLE_POST
import com.evanemran.kickoff.database.FirebaseDbConstants.TABLE_USER
import com.evanemran.kickoff.globals.GlobalUser
import com.evanemran.kickoff.listeners.EventListeners
import com.evanemran.kickoff.listeners.PostListener
import com.evanemran.kickoff.models.PostData
import com.evanemran.kickoff.models.User
import com.evanemran.kickoff.utils.PostDialog
import com.github.ybq.android.spinkit.sprite.Sprite
import com.github.ybq.android.spinkit.style.Circle
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import id.zelory.compressor.Compressor
import kotlinx.android.synthetic.main.fragment_blog.*
import kotlinx.android.synthetic.main.fragment_feed.*
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*

class FeedFragment : Fragment() {

    lateinit var databaseReference: DatabaseReference//
    lateinit var postsReference: DatabaseReference
    lateinit var firebaseDatabase: FirebaseDatabase//
    lateinit var userReference: DatabaseReference
    lateinit var storage: FirebaseStorage//
    var postDataList: MutableList<PostData> = mutableListOf()
    var newPost: PostData = PostData()
    lateinit var storageReference: StorageReference//
    var user: User = User()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_feed, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val animation: Sprite = Circle()
        spin_kit_feed.setIndeterminateDrawable(animation)

        storage = FirebaseStorage.getInstance()
        storageReference = storage.reference

//        user = SharedPrefs(context).user


        firebaseDatabase = FirebaseDatabase.getInstance()
        databaseReference = firebaseDatabase.getReference(TABLE_USER)

        val uID = FirebaseAuth.getInstance().currentUser!!.uid
        if (uID.isNotEmpty()) {
            getUserData(uID)
        }

        getUserData(uID)

        showPosts()


        fab_add_post.setOnClickListener {
            showPostDialog()
        }

    }

    private fun showPostDialog() {
        val postDialog = PostDialog(postDataClickListener)
        postDialog.show(childFragmentManager, "post")
    }

    private val postDataClickListener: PostListener = object : PostListener {
        override fun onPostClicked(data: PostData, imageUri: Uri?) {
            newPost = data
            val format = SimpleDateFormat("EEE, d MMM yyyy HH:mm a")
            val date = Date()
            newPost.posTTime = (format.format(date))
            if (imageUri != null) {
                uploadImage(imageUri)
            } else {
                newPost.postedBy = (user)
                postsReference = firebaseDatabase.getReference(TABLE_POST)
                val key: String = postsReference.push().key+""
                newPost.postId = (key)
                postsReference.child(key).setValue(newPost)
                Toast.makeText(requireContext(), "Posted!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun getUserData(uID: String) {
        try{
            val dbQuery = databaseReference
                .orderByChild("userId")
                .equalTo(uID)
            dbQuery.addListenerForSingleValueEvent(
                databaseReference.addValueEventListener(object : ValueEventListener {
                    override fun onDataChange(dataSnapshot: DataSnapshot) {
                        for (postSnapshot in dataSnapshot.children) {
                            user = postSnapshot.getValue(User::class.java)!!
                            GlobalUser.getInstance().data = (user)
                        }
                    }

                    override fun onCancelled(databaseError: DatabaseError) {}
                })
            )
        }
        catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun getFileExtension(uri: Uri): String? {
        val contentResolver: ContentResolver = requireActivity().contentResolver
        val mime = MimeTypeMap.getSingleton()
        return mime.getExtensionFromMimeType(contentResolver.getType(uri))
    }

    private fun uploadImage(filePath: Uri?) {
        if (filePath != null) {

            // Code for showing progressDialog while uploading
            val progressDialog = ProgressDialog(requireContext())
            progressDialog.setTitle("Uploading...")
            progressDialog.show()
            progressDialog.setCanceledOnTouchOutside(false)

            // Defining the child of storageReference
            val ref: StorageReference = storageReference!!
                .child(
                    "images/"
                            + System.currentTimeMillis() + "." + getFileExtension(filePath)
                )

            // adding listeners on upload
            // or failure of image
            compressImage(filePath)?.let {
                ref.putFile(it)
                    .addOnSuccessListener(
                        OnSuccessListener<Any?> {
                            ref.downloadUrl.addOnSuccessListener(OnSuccessListener<Uri> { uri ->
                                newPost!!.image = (uri.toString())
                                newPost!!.postedBy = (user)
                                postsReference = firebaseDatabase!!.getReference(TABLE_POST)
                                val key: String = postsReference!!.push().key + ""
                                newPost!!.postId = (key)
                                postsReference!!.child(key).setValue(newPost)
                                Toast.makeText(requireContext(), "Posted!", Toast.LENGTH_SHORT).show()
                                progressDialog.dismiss()
                                Toast.makeText(
                                    requireContext(),
                                    "Image Uploaded!!",
                                    Toast.LENGTH_SHORT
                                ).show()
                            })

                            // Image uploaded successfully
                            // Dismiss dialog
                        })
                    .addOnFailureListener(OnFailureListener { e -> // Error, Image not uploaded
                        progressDialog.dismiss()
                        Toast
                            .makeText(
                                requireContext(),
                                "Failed " + e.message,
                                Toast.LENGTH_SHORT
                            )
                            .show()
                    })
                    .addOnProgressListener { taskSnapshot ->

                        // Progress Listener for loading
                        // percentage on the dialog box
                        val progress: Double = ((100.0
                                * taskSnapshot.bytesTransferred
                                / taskSnapshot.totalByteCount))
                        progressDialog.setMessage(
                            ("Uploaded "
                                    + progress.toInt() + "%")
                        )
                    }
            }
        }
    }

    private fun showPosts() {
        databaseReference = FirebaseDatabase.getInstance().getReference(TABLE_POST)

        var eventListeners: EventListeners = EventListeners(context, databaseReference, childFragmentManager)

        databaseReference!!.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                postDataList.clear()
                for (postSnapshot in dataSnapshot.children) {
                    val post = postSnapshot.getValue(PostData::class.java)
                    if (post != null) {
                        postDataList.add(post)
                    }
                }
                postDataList.reverse()
                recycler_home.setHasFixedSize(true)
                recycler_home.layoutManager = LinearLayoutManager(
                    context,
                    LinearLayoutManager.VERTICAL,
                    false
                )
                val adapter = PostsAdapter(requireContext(), postDataList, eventListeners.reactionListener)
                adapter.setHasStableIds(true)
                recycler_home.adapter = adapter
                spin_kit_feed.visibility = View.GONE
                scrollView_feed.visibility = View.VISIBLE
            }

            override fun onCancelled(databaseError: DatabaseError) {}
        })
        userReference = FirebaseDatabase.getInstance().getReference(TABLE_USER)
//        userReference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                userList.clear();
//                for (DataSnapshot postSnapshot : dataSnapshot.getChildren())
//                {
//                    User user = postSnapshot.getValue(User.class);
//                    userList.add(user);
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });
    }

    private fun compressImage(uri: Uri): Uri? {
        var compressedUri: Uri? = null
        compressedUri = try {
            val quality = 70
            val width = 720
            //            int height = Integer.valueOf(txtHeight.getText().toString());
            val compressed: File = Compressor(requireContext())
                .setMaxWidth(width)
                .setQuality(quality)
                .setCompressFormat(Bitmap.CompressFormat.JPEG)
                .compressToFile(File(uri.path))
            Uri.fromFile(compressed)
        } catch (e: IOException) {
            e.printStackTrace()
            uri
        }
        return compressedUri
    }
}