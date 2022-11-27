package com.evanemran.kickoff

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.evanemran.kickoff.database.FirebaseDbConstants
import com.evanemran.kickoff.manager.RequestManager
import com.evanemran.kickoff.models.User
import com.github.ybq.android.spinkit.sprite.Sprite
import com.github.ybq.android.spinkit.style.Circle
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.UserProfileChangeRequest
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_auth.*
import kotlinx.android.synthetic.main.activity_register.*
import kotlinx.android.synthetic.main.fragment_match.*
import kotlinx.android.synthetic.main.fragment_register.*

class RegisterActivity : AppCompatActivity() {
    private var mAuth: FirebaseAuth? = null
    var databaseReference: DatabaseReference? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val animation: Sprite = Circle()
        spin_kit_button_reg.setIndeterminateDrawable(animation)

        mAuth = FirebaseAuth.getInstance()
        databaseReference = FirebaseDatabase.getInstance().getReference(FirebaseDbConstants.TABLE_USER)

        button_register_card.setOnClickListener {
            val user = User()
            user.userName = editText_name.text.toString()
            user.userMail = editText_mail.text.toString()
            user.userPass = editText_password.text.toString()
            user.userPassConfirm = editText_confirm_password.text.toString()
//            user.userName = "dsadsa"
//            user.userMail ="EVAn48@gmail.com"
//            user.userPass = "123456"
//            user.userPassConfirm = "123456"
            if (!user.validate()) {
                Toast.makeText(this, "Password should be 6 characters!", Toast.LENGTH_SHORT).show()
            }
            else {
                spin_kit_button_reg.visibility = View.VISIBLE
                button_register_text.visibility = View.GONE
                authenticate(user.userName, user.userMail, user.userPass)
            }
        }

        textView_auth_switcher_reg.setOnClickListener {
            startActivity(Intent(this@RegisterActivity, LoginActivity::class.java))
            this.finish()
        }
    }

    private fun authenticate(userName: String, userMail: String, userPass: String) {
        mAuth!!.createUserWithEmailAndPassword(userMail, userPass)
            .addOnCompleteListener(
                this
            ) { task ->
                if (task.isSuccessful) {
                    task.result.user?.let { createNewUser(it, userName) }
                    Toast.makeText(this, "Authenticated", Toast.LENGTH_SHORT).show()
                    val user = mAuth!!.currentUser
                    val profileUpdates = UserProfileChangeRequest.Builder()
                        .setDisplayName(userName).build()
                    user!!.updateProfile(profileUpdates).addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            val gUser: User = User()
                            gUser.userName = userName
                            gUser.userMail = userMail
//                            SharedPrefs(requireContext()).saveUser(gUser)
                            Log.d(ContentValues.TAG, "User profile updated.")
                            spin_kit_button_reg.visibility = View.GONE
                            button_register_text.visibility = View.VISIBLE
//                            Toast.makeText(this, "User profile updated", Toast.LENGTH_SHORT).show()
//                            requireActivity().finish()
                        }
                    }
                    // Sign in success, update UI with the signed-in user's information
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(ContentValues.TAG, "createUserWithEmail:failure", task.exception)

                    Toast.makeText(this, "Authentication failed.", Toast.LENGTH_SHORT).show()
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
//        Toast.makeText(this, "added", Toast.LENGTH_SHORT).show()
    }

    private fun addUser(user: User) {
        databaseReference!!.child(user.userId).setValue(user)
//        Toast.makeText(this, "Info Added to database", Toast.LENGTH_SHORT).show()
    }
}