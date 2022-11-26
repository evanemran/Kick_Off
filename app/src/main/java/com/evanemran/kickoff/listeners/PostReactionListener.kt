package com.evanemran.kickoff.listeners

interface PostReactionListener<T> {
    fun onLikeClicked(data: T?)
    fun onShareClicked(data: T?)
    fun onCommentClicked(data: T?)
}