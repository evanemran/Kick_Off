package com.evanemran.kickoff

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.evanemran.kickoff.fragments.LoginFragment
import com.evanemran.kickoff.fragments.RegisterFragment
import com.evanemran.kickoff.models.User
import com.github.ybq.android.spinkit.sprite.Sprite
import com.github.ybq.android.spinkit.style.Circle
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_auth.*
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_register.*
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.android.synthetic.main.fragment_login.editText_mail
import kotlinx.android.synthetic.main.fragment_login.editText_password

class LoginActivity : AppCompatActivity() {
    private var mAuth: FirebaseAuth? = null
    var firebaseAuth: FirebaseAuth? = null
    var authStateListener: FirebaseAuth.AuthStateListener? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val animation: Sprite = Circle()
        spin_kit_button_login.setIndeterminateDrawable(animation)

        firebaseAuth = FirebaseAuth.getInstance()
        mAuth = FirebaseAuth.getInstance()

        authStateListener = FirebaseAuth.AuthStateListener { firebaseAuth ->
            val firebaseUser = firebaseAuth.currentUser
            if (firebaseUser != null) {
                Toast.makeText(this, "Welcome!", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                this.finish()
            } else {
                Toast.makeText(this, "Please Login", Toast.LENGTH_SHORT).show()
            }
        }

        button_login_card.setOnClickListener {

            val email: String = editText_mail.text.toString()
            val password: String = editText_password.text.toString()
            if (isValidInputs(email, password)) {
                spin_kit_button_login.visibility = View.VISIBLE
                button_login_text.visibility = View.GONE
                login(email, password)
            }

//            val loginBody = LoginRequest(editText_mail.text.toString(), editText_password.text.toString())
//            if (loginBody.validate()){
//                manager.login(loginResponseListener, loginBody)
//            }
//            else{
//                Toast.makeText(context, "Invalid Inputs", Toast.LENGTH_SHORT).show()
//            }
        }

        textView_auth_switcher_login.setOnClickListener {
            startActivity(Intent(this@LoginActivity, RegisterActivity::class.java))
            this.finish()
        }
    }

    private fun isValidInputs(email: String, password: String): Boolean {
        return if (email.isEmpty()) {
            editText_mail.error = ("Enter email")
            false
        } else if (password.isEmpty()) {
            editText_password.error = ("Enter password")
            false
        } else true
    }

    private fun login(email: String, password: String) {
        mAuth!!.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(
                this
            ) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(ContentValues.TAG, "signInWithEmail:success")
                    val user = mAuth!!.currentUser

                    val gUser: User = User()
                    gUser.userName = user!!.displayName.toString()
                    gUser.userMail = user.email.toString()

                    spin_kit_button_login.visibility = View.VISIBLE
                    button_login_text.visibility = View.GONE

                    com.evanemran.kickoff.sharedprefs.SharedPrefs(this).saveUser(gUser)
//                    Toast.makeText(
//                        requireContext(), user!!.displayName + " Name",
//                        Toast.LENGTH_SHORT
//                    ).show()
                    //                            updateUI(user);
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(ContentValues.TAG, "signInWithEmail:failure", task.exception)
                    Toast.makeText(
                        this, "Authentication failed.",
                        Toast.LENGTH_SHORT
                    ).show()
                    //                            updateUI(null);
                }
            }
    }

    override fun onStart() {
        super.onStart()
        firebaseAuth!!.addAuthStateListener(authStateListener!!)
    }
}