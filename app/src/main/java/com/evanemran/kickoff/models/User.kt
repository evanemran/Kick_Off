package com.evanemran.kickoff.models

class User() {
    var userId: String = ""
    var userName: String = ""
    var userMail: String = ""
    var userPass: String = ""
    var userPassConfirm: String = ""
    var userPhone: String = ""
    var userPhoto: String = ""


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