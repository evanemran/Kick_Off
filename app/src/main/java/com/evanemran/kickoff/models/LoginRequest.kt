package com.evanemran.kickoff.models

class LoginRequest(loginMail: String, loginPassword: String) {
    var email: String = loginMail
    var password: String = loginPassword


    fun validate(): Boolean {
        if (!email.contains("@")){
            return false
        }
        else if (password.length<8){
            return false
        }
        return true
    }
}