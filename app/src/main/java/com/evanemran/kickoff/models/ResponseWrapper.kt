package com.evanemran.kickoff.models

class ResponseWrapper<T> {
    var status: String = ""
    var message: String = ""
    var data: T? = null
}