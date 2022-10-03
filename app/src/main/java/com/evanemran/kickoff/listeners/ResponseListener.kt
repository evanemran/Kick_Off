package com.evanemran.kickoff.listeners

interface ResponseListener<T> {
    fun didFetch(message: String, response: T)
    fun didError(message: String)
}