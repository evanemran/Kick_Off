package com.evanemran.kickoff.listeners

import android.net.Uri
import com.evanemran.kickoff.models.PostData

interface PostListener {
    fun onPostClicked(data: PostData, imageUri: Uri?)
}