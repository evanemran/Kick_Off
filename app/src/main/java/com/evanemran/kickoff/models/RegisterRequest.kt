package com.evanemran.kickoff.models

class RegisterRequest(regName: String, regMail: String, regPass: String, regConfPass: String) {
    var name: String = regName
    var email: String = regMail
    var password: String = regPass
    var passwordConfirm: String = regConfPass


    fun validate(): Boolean {
        if (!email.contains("@")){
            return false
        }
        else if (name.isEmpty()){
            return false
        }
        else if (password!=passwordConfirm){
            return false
        }
        else if (password.length<8){
            return false
        }
        return true
    }
}