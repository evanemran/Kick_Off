package com.evanemran.kickoff.models

class PostData {
    var postId = ""
    var postedBy = User()
    var postBody = ""
    var posTTime = ""
    var hasImage = false
    var isLiked = false
    var image = ""
    var likes = 0
    var commentsCount = 0
    var shareCount = 0
    var posterAvatar = ""
    var comments: List<CommentData> = ArrayList<CommentData>()
}