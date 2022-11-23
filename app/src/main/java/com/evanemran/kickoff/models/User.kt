package com.evanemran.kickoff.models

class User(name: String, mail: String, password: String) {
    var userId: Int = 0
    var userName: String = name
    var userMail: String = mail
    var userPass: String = password
    var userPassConfirm: String = ""
    var userPhone: String = ""


    fun validate(): Boolean {
        if (!userMail.contains("@")){
            return false
        }
        else if (userName.isEmpty()){
            return false
        }
        else if (userPass!=userPassConfirm){
            return false
        }
        else if (userPass.length<6){
            return false
        }
        return true
    }
}