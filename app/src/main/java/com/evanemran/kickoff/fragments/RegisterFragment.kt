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
import com.evanemran.kickoff.listeners.ResponseListener
import com.evanemran.kickoff.manager.RequestManager
import com.evanemran.kickoff.models.*
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.android.synthetic.main.fragment_login.editText_mail
import kotlinx.android.synthetic.main.fragment_login.editText_password
import kotlinx.android.synthetic.main.fragment_register.*

class RegisterFragment : Fragment() {

//    var view: View? = View(context)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_register, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var manager: RequestManager = RequestManager(requireContext())

        button_register.setOnClickListener {
            var registerBody = RegisterRequest(editText_name.text.toString() ,editText_mail.text.toString(), editText_password.text.toString(), editText_confirm_password.text.toString())
            if (registerBody.validate()){
                manager.register(registerResponseListener, registerBody)
            }
            else{
                Toast.makeText(context, "Invalid Inputs", Toast.LENGTH_SHORT).show()
            }
        }

    }

    private val registerResponseListener: ResponseListener<ResponseWrapper<RegisterResponse>> = object :
        ResponseListener<ResponseWrapper<RegisterResponse>> {
        override fun didFetch(message: String, response: ResponseWrapper<RegisterResponse>) {
            startActivity(Intent(context, MainActivity::class.java))
            Toast.makeText(context, response.message.toString(), Toast.LENGTH_SHORT).show()
        }
        override fun didError(message: String) {
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
        }
    }
}