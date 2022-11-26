package com.evanemran.kickoff.fragments

import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.evanemran.kickoff.MainActivity
import com.evanemran.kickoff.R
import com.evanemran.kickoff.database.FirebaseDbConstants
import com.evanemran.kickoff.manager.RequestManager
import com.evanemran.kickoff.models.*
import com.evanemran.kickoff.sharedprefs.SharedPrefs
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.UserProfileChangeRequest
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.android.synthetic.main.fragment_login.editText_mail
import kotlinx.android.synthetic.main.fragment_login.editText_password
import kotlinx.android.synthetic.main.fragment_register.*

class RegisterFragment : Fragment() {

    //    var view: View? = View(context)
    private var mAuth: FirebaseAuth? = null
    var databaseReference: DatabaseReference? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_register, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mAuth = FirebaseAuth.getInstance()
        databaseReference = FirebaseDatabase.getInstance().getReference(FirebaseDbConstants.TABLE_USER)

        var manager: RequestManager = RequestManager(requireContext())

        button_register.setOnClickListener {
            var user = User()
            user.userName = editText_name.text.toString()
            user.userMail = editText_mail.text.toString()
            user.userPass = editText_password.text.toString()
            user.userPassConfirm = editText_confirm_password.text.toString()

            if (user.validate()) {
                authenticate(user.userName, user.userMail, user.userPass)
            }
        }

//        button_register.setOnClickListener {
//            var registerBody = RegisterRequest(editText_name.text.toString() ,editText_mail.text.toString(), editText_password.text.toString(), editText_confirm_password.text.toString())
//            if (registerBody.validate()){
//                manager.register(registerResponseListener, registerBody)
//            }
//            else{
//                Toast.makeText(context, "Invalid Inputs", Toast.LENGTH_SHORT).show()
//            }
//        }

    }

    private fun authenticate(userName: String, userMail: String, userPass: String) {
        mAuth!!.createUserWithEmailAndPassword(userMail, userPass)
            .addOnCompleteListener {
                    task ->
                if (task.isSuccessful) {
                    task.result.user?.let { createNewUser(it, userName) }
                    Toast.makeText(requireContext(), "Authenticated", Toast.LENGTH_SHORT).show()
                    val user = mAuth!!.currentUser
                    val profileUpdates = UserProfileChangeRequest.Builder()
                        .setDisplayName(userName).build()
                    user!!.updateProfile(profileUpdates).addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            val gUser: User = User()
                            gUser.userName = userName
                            gUser.userMail = userMail
                            SharedPrefs(requireContext()).saveUser(gUser)
                            Log.d(ContentValues.TAG, "User profile updated.")
                            Toast.makeText(requireContext(), "User profile updated", Toast.LENGTH_SHORT).show()
//                            requireActivity().finish()
                        }
                    }
                    // Sign in success, update UI with the signed-in user's information
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(ContentValues.TAG, "createUserWithEmail:failure", task.exception)

                    Toast.makeText(context, "Authentication failed.", Toast.LENGTH_SHORT).show()
                }
            }
    }

    private fun createNewUser(user: FirebaseUser, name: String) {
        val localUser: User = User()
        localUser.userId = user.uid
        localUser.userName = name
        addUser(
            localUser
        )
        Toast.makeText(context, "added", Toast.LENGTH_SHORT).show()
    }

    private fun addUser(user: User) {
        databaseReference!!.child(user.userId).setValue(user)
        Toast.makeText(requireContext(), "Info Added to database", Toast.LENGTH_SHORT).show()
    }

//    private val registerResponseListener: ResponseListener<ResponseWrapper<RegisterResponse>> =
//        object :
//            ResponseListener<ResponseWrapper<RegisterResponse>> {
//            override fun didFetch(message: String, response: ResponseWrapper<RegisterResponse>) {
//                startActivity(Intent(context, MainActivity::class.java))
//                SharedPrefs(requireContext()).saveToken(response.data!!.token)
//                SharedPrefs(requireContext()).saveName(editText_name.text.toString())
//                requireActivity().finish()
//                Toast.makeText(context, response.message.toString(), Toast.LENGTH_SHORT).show()
//            }
//
//            override fun didError(message: String) {
//                Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
//            }
//        }
}