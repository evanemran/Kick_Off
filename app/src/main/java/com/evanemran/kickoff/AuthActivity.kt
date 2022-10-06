package com.evanemran.kickoff

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.evanemran.kickoff.fragments.LoginFragment
import com.evanemran.kickoff.fragments.RegisterFragment
import kotlinx.android.synthetic.main.activity_auth.*

class AuthActivity : AppCompatActivity() {
    var selectedFragment: Fragment = LoginFragment()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)

        switchFragment(selectedFragment)

        textView_auth_switcher.setOnClickListener {
            if (selectedFragment is LoginFragment) {
                textView_auth_switcher.text = this.getString(R.string.login_now)
                textView_header.text = this.getString(R.string.register)
                selectedFragment = RegisterFragment()
            }
            else {
                textView_auth_switcher.text = this.getString(R.string.register_now)
                textView_header.text = this.getString(R.string.login)
                selectedFragment = LoginFragment()
            }
            switchFragment(selectedFragment)
        }

    }

    private fun switchFragment(selectedFragment: Fragment) {

        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
            .setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out)
        fragmentTransaction.replace(R.id.fragment_container, selectedFragment)
        fragmentTransaction.commit()

    }
}