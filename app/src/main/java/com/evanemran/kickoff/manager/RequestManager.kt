package com.evanemran.kickoff.manager

import android.content.Context
import com.evanemran.kickoff.listeners.ResponseListener
import com.evanemran.kickoff.models.RegisterRequest
import com.evanemran.kickoff.models.RegisterResponse
import com.evanemran.kickoff.models.ResponseWrapper
import com.evanemran.kickoff.models.TeamInfo
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

class RequestManager(context: Context) {
    var retrofit = Retrofit.Builder()
        .baseUrl("http://api.cup2022.ir/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()


    fun register(listener: ResponseListener<ResponseWrapper<RegisterResponse>>, body: RegisterRequest) {
        val call = retrofit.create(Register::class.java).register(body)
        call.enqueue(object : Callback<ResponseWrapper<RegisterResponse>> {
            override fun onResponse(
                call: Call<ResponseWrapper<RegisterResponse>>,
                response: Response<ResponseWrapper<RegisterResponse>>
            ) {
                if (!response.isSuccessful){
                    listener.didError(response.message())
                    return
                }
                response.body()?.let { listener.didFetch(response.message(), it) }
            }

            override fun onFailure(call: Call<ResponseWrapper<RegisterResponse>>, t: Throwable) {
                t.message?.let { listener.didError(it) }
            }

        })
    }


    private interface Register {
        @POST("api/v1/user")
        fun register(
            body: RegisterRequest
        ):Call<ResponseWrapper<RegisterResponse>>

        @POST("api/v1/user/login")
        fun login(
            body: RegisterRequest
        ):Call<ResponseWrapper<RegisterResponse>>

        @GET("api/v1/team")
        fun getTeamsInfo():Call<ResponseWrapper<List<TeamInfo>>>

        @GET("api/v1/team/{id}")
        fun getAllTeamsInfo(
            @Path("id") id: String
        ):Call<ResponseWrapper<TeamInfo>>

        @GET("api/v1/match")
        fun getAllMatchInfo(
        ):Call<ResponseWrapper<TeamInfo>>
    }

}
