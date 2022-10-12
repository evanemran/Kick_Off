package com.evanemran.kickoff.manager

import android.content.Context
import com.evanemran.kickoff.listeners.ResponseListener
import com.evanemran.kickoff.models.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

class BlobManager(var context: Context) {
    var retrofit = Retrofit.Builder()
        .baseUrl("https://jsonblob.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()


    fun getAllWinners(listener: ResponseListener<List<WinnerData>>) {
        val call = retrofit.create(BlobManager.BlobInterface::class.java).getWinners()
        call.enqueue(object : Callback<List<WinnerData>> {
            override fun onResponse(
                call: Call<List<WinnerData>>,
                response: Response<List<WinnerData>>
            ) {
                if (!response.isSuccessful){
                    listener.didError(response.message())
                    return
                }
                response.body()?.let { listener.didFetch(response.message(), it) }
            }

            override fun onFailure(call: Call<List<WinnerData>>, t: Throwable) {
                t.message?.let { listener.didError(it) }
            }

        })
    }


    interface BlobInterface {
        @GET("api/1029685401790726144")
        fun getWinners(): Call<List<WinnerData>>
    }

}