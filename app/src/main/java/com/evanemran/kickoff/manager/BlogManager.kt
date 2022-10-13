package com.evanemran.kickoff.manager

import android.content.Context
import com.evanemran.kickoff.R
import com.evanemran.kickoff.listeners.ResponseListener
import com.evanemran.kickoff.models.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

class BlogManager(var context: Context) {
    var retrofit = Retrofit.Builder()
        .baseUrl("https://newsapi.org/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun getBlogs(listener: ResponseListener<BlogResponse>) {
        val call = retrofit.create(BlogManager.BlogInterface::class.java).getArticles("fifa world cup", context.getString(
            R.string.blog_api_key))
        call.enqueue(object : Callback<BlogResponse> {
            override fun onResponse(
                call: Call<BlogResponse>,
                response: Response<BlogResponse>
            ) {
                if (!response.isSuccessful){
                    listener.didError(response.message())
                    return
                }
                response.body()?.let { listener.didFetch(response.message(), it) }
            }

            override fun onFailure(call: Call<BlogResponse>, t: Throwable) {
                t.message?.let { listener.didError(it) }
            }

        })
    }


    interface BlogInterface {
        @GET("v2/everything")
        fun getArticles(
            @Query("q") q: String,
            @Query("apiKey") apiKey: String
        ): Call<BlogResponse>
    }

}