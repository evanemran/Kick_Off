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
import retrofit2.http.Path

class BlobManager(var context: Context) {
    var retrofit = Retrofit.Builder()
        .baseUrl("https://jsonblob.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun getDetails(listener: ResponseListener<List<WinnerDetails>>) {
        val call = retrofit.create(BlobManager.BlobInterface::class.java).getWinnerDetails()
        call.enqueue(object : Callback<List<WinnerDetails>> {
            override fun onResponse(
                call: Call<List<WinnerDetails>>,
                response: Response<List<WinnerDetails>>
            ) {
                if (!response.isSuccessful){
                    listener.didError(response.message())
                    return
                }
                response.body()?.let { listener.didFetch(response.message(), it) }
            }

            override fun onFailure(call: Call<List<WinnerDetails>>, t: Throwable) {
                t.message?.let { listener.didError(it) }
            }

        })
    }


    interface BlobInterface {
        @GET("api/1029685401790726144")
        fun getWinners(): Call<List<WinnerData>>

        @GET("api/1030175605781708800")
        fun getWinnerDetails(): Call<List<WinnerDetails>>
    }

}