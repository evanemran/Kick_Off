package com.evanemran.kickoff.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.evanemran.kickoff.MainActivity
import com.evanemran.kickoff.R
import com.evanemran.kickoff.constants.SharedPrefs
import com.evanemran.kickoff.listeners.ResponseListener
import com.evanemran.kickoff.manager.RequestManager
import com.evanemran.kickoff.models.LoginRequest
import com.evanemran.kickoff.models.LoginResponse
import com.evanemran.kickoff.models.RegisterRequest
import com.evanemran.kickoff.models.ResponseWrapper
import kotlinx.android.synthetic.main.fragment_login.*

class LoginFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var manager: RequestManager = RequestManager(requireContext())

        button_login.setOnClickListener {
            var loginBody = LoginRequest(editText_mail.text.toString(), editText_password.text.toString())
            if (loginBody.validate()){
                manager.login(loginResponseListener, loginBody)
            }
            else{
                Toast.makeText(context, "Invalid Inputs", Toast.LENGTH_SHORT).show()
            }
        }

    }

    private val loginResponseListener: ResponseListener<ResponseWrapper<LoginResponse>> = object : ResponseListener<ResponseWrapper<LoginResponse>>{
        override fun didFetch(message: String, response: ResponseWrapper<LoginResponse>) {
            startActivity(Intent(context, MainActivity::class.java))
            SharedPrefs(requireContext()).saveToken(response.data!!.token)
        }
        override fun didError(message: String) {
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
        }
    }
}