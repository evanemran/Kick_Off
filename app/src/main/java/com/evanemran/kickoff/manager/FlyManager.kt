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

class FlyManager(var context: Context) {
    var retrofit = Retrofit.Builder()
        .baseUrl("https://world-cup-json-2022.fly.dev/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()


    fun getAllMatches(listener: ResponseListener<List<MatchDataFly>>) {
        val call = retrofit.create(FlyManager.FlyInterface::class.java).getMatches()
        call.enqueue(object : Callback<List<MatchDataFly>> {
            override fun onResponse(
                call: Call<List<MatchDataFly>>,
                response: Response<List<MatchDataFly>>
            ) {
                if (!response.isSuccessful){
                    listener.didError(response.message())
                    return
                }
                response.body()?.let { listener.didFetch(response.message(), it) }
            }

            override fun onFailure(call: Call<List<MatchDataFly>>, t: Throwable) {
                t.message?.let { listener.didError(it) }
            }

        })
    }

    fun getTodayMatches(listener: ResponseListener<List<MatchDataFly>>) {
        val call = retrofit.create(FlyManager.FlyInterface::class.java).getTodayMatches()
        call.enqueue(object : Callback<List<MatchDataFly>> {
            override fun onResponse(
                call: Call<List<MatchDataFly>>,
                response: Response<List<MatchDataFly>>
            ) {
                if (!response.isSuccessful){
                    listener.didError(response.message())
                    return
                }
                response.body()?.let { listener.didFetch(response.message(), it) }
            }

            override fun onFailure(call: Call<List<MatchDataFly>>, t: Throwable) {
                t.message?.let { listener.didError(it) }
            }

        })
    }

    fun getStandings(listener: ResponseListener<FlyStandingResponse>) {
        val call = retrofit.create(FlyManager.FlyInterface::class.java).getStats()
        call.enqueue(object : Callback<FlyStandingResponse> {
            override fun onResponse(
                call: Call<FlyStandingResponse>,
                response: Response<FlyStandingResponse>
            ) {
                if (!response.isSuccessful){
                    listener.didError(response.message())
                    return
                }
                response.body()?.let { listener.didFetch(response.message(), it) }
            }

            override fun onFailure(call: Call<FlyStandingResponse>, t: Throwable) {
                t.message?.let { listener.didError(it) }
            }

        })
    }


    interface FlyInterface {
        @GET("matches")
        fun getMatches(): Call<List<MatchDataFly>>

        @GET("matches/today")
        fun getTodayMatches(): Call<List<MatchDataFly>>

        @GET("teams")
        fun getStats(): Call<FlyStandingResponse>
    }

}