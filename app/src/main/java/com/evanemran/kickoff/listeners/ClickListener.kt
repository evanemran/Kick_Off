package com.evanemran.kickoff.listeners

interface ClickListener<T> {
    fun onClicked(data: T)
}