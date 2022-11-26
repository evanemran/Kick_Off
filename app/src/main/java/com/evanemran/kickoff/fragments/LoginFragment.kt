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
import com.evanemran.kickoff.manager.RequestManager
import com.evanemran.kickoff.models.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuth.AuthStateListener
import kotlinx.android.synthetic.main.fragment_login.*

class LoginFragment : Fragment() {

    private var mAuth: FirebaseAuth? = null
    var firebaseAuth: FirebaseAuth? = null
    private var authStateListener: AuthStateListener? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        firebaseAuth = FirebaseAuth.getInstance()
        mAuth = FirebaseAuth.getInstance()

        authStateListener = AuthStateListener { firebaseAuth ->
            val firebaseUser = firebaseAuth.currentUser
            if (firebaseUser != null) {
                Toast.makeText(requireContext(), "Welcome!", Toast.LENGTH_SHORT).show()
                val intent = Intent(requireContext(), MainActivity::class.java)
                startActivity(intent)
                requireActivity().finish()
            } else {
                Toast.makeText(requireContext(), "Please Login", Toast.LENGTH_SHORT).show()
            }
        }

        val manager: RequestManager = RequestManager(requireContext())

        button_login.setOnClickListener {

            val email: String = editText_mail.text.toString()
            val password: String = editText_password.text.toString()
            if (isValidInputs(email, password)) {
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
                requireActivity()
            ) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(ContentValues.TAG, "signInWithEmail:success")
                    val user = mAuth!!.currentUser

                    val gUser: User = User()
                    gUser.userName = user!!.displayName.toString()
                    gUser.userMail = user.email.toString()

                    com.evanemran.kickoff.sharedprefs.SharedPrefs(requireContext()).saveUser(gUser)
//                    Toast.makeText(
//                        requireContext(), user!!.displayName + " Name",
//                        Toast.LENGTH_SHORT
//                    ).show()
                    //                            updateUI(user);
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(ContentValues.TAG, "signInWithEmail:failure", task.exception)
                    Toast.makeText(
                        requireContext(), "Authentication failed.",
                        Toast.LENGTH_SHORT
                    ).show()
                    //                            updateUI(null);
                }
            }
    }

//    private val loginResponseListener: ResponseListener<ResponseWrapper<LoginResponse>> = object : ResponseListener<ResponseWrapper<LoginResponse>>{
//        override fun didFetch(message: String, response: ResponseWrapper<LoginResponse>) {
//            startActivity(Intent(context, MainActivity::class.java)
//                .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
//            SharedPrefs(requireContext()).saveToken(response.data!!.token)
//            requireActivity().finish()
//        }
//        override fun didError(message: String) {
//            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
//        }
//    }

    override fun onStart() {
        super.onStart()
        firebaseAuth!!.addAuthStateListener(authStateListener!!)
    }
}
