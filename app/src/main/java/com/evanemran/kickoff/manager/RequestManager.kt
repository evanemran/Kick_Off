package com.evanemran.kickoff.manager

import android.content.Context
import com.evanemran.kickoff.listeners.ResponseListener
import com.evanemran.kickoff.models.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

class RequestManager(var context: Context) {
    var retrofit = Retrofit.Builder()
        .baseUrl("http://api.cup2022.ir/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

//    var gToken: String = "Bearer " + SharedPrefs(context).getToken()

    fun register(listener: ResponseListener<ResponseWrapper<RegisterResponse>>, body: RegisterRequest) {
        val call = retrofit.create(ApiInterface::class.java).register(body)
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

    fun login(listener: ResponseListener<ResponseWrapper<LoginResponse>>, body: LoginRequest) {
        val call = retrofit.create(ApiInterface::class.java).login(body)
        call.enqueue(object : Callback<ResponseWrapper<LoginResponse>> {
            override fun onResponse(
                call: Call<ResponseWrapper<LoginResponse>>,
                response: Response<ResponseWrapper<LoginResponse>>
            ) {
                if (!response.isSuccessful){
                    listener.didError(response.message())
                    return
                }
                response.body()?.let { listener.didFetch(response.message(), it) }
            }

            override fun onFailure(call: Call<ResponseWrapper<LoginResponse>>, t: Throwable) {
                t.message?.let { listener.didError(it) }
            }

        })
    }

//    fun getAllTeams(listener: ResponseListener<ResponseWrapper<List<TeamInfo>>>) {
//        val call = retrofit.create(ApiInterface::class.java).getAllTeamInfo(gToken)
//        call.enqueue(object : Callback<ResponseWrapper<List<TeamInfo>>> {
//            override fun onResponse(
//                call: Call<ResponseWrapper<List<TeamInfo>>>,
//                response: Response<ResponseWrapper<List<TeamInfo>>>
//            ) {
//                if (!response.isSuccessful){
//                    listener.didError(response.message())
//                    return
//                }
//                response.body()?.let { listener.didFetch(response.message(), it) }
//            }
//
//            override fun onFailure(call: Call<ResponseWrapper<List<TeamInfo>>>, t: Throwable) {
//                t.message?.let { listener.didError(it) }
//            }
//
//        })
//    }

    fun getSpecificTeam(listener: ResponseListener<ResponseWrapper<TeamInfo>>, teamId: String) {
        val call = retrofit.create(ApiInterface::class.java).getTeamInfo(teamId)
        call.enqueue(object : Callback<ResponseWrapper<TeamInfo>> {
            override fun onResponse(
                call: Call<ResponseWrapper<TeamInfo>>,
                response: Response<ResponseWrapper<TeamInfo>>
            ) {
                if (!response.isSuccessful){
                    listener.didError(response.message())
                    return
                }
                response.body()?.let { listener.didFetch(response.message(), it) }
            }

            override fun onFailure(call: Call<ResponseWrapper<TeamInfo>>, t: Throwable) {
                t.message?.let { listener.didError(it) }
            }

        })
    }

//    fun getStandings(listener: ResponseListener<ResponseWrapper<List<StandingsResponse>>>) {
//        val call = retrofit.create(ApiInterface::class.java).getGroupStandings(gToken)
//        call.enqueue(object : Callback<ResponseWrapper<List<StandingsResponse>>> {
//            override fun onResponse(
//                call: Call<ResponseWrapper<List<StandingsResponse>>>,
//                response: Response<ResponseWrapper<List<StandingsResponse>>>
//            ) {
//                if (!response.isSuccessful){
//                    listener.didError(response.message())
//                    return
//                }
//                response.body()?.let { listener.didFetch(response.message(), it) }
//            }
//
//            override fun onFailure(call: Call<ResponseWrapper<List<StandingsResponse>>>, t: Throwable) {
//                t.message?.let { listener.didError(it) }
//            }
//
//        })
//    }

//    fun getAllMatches(listener: ResponseListener<ResponseWrapper<List<MatchData>>>) {
//        val call = retrofit.create(ApiInterface::class.java).getAllMatchInfo(gToken)
//        call.enqueue(object : Callback<ResponseWrapper<List<MatchData>>> {
//            override fun onResponse(
//                call: Call<ResponseWrapper<List<MatchData>>>,
//                response: Response<ResponseWrapper<List<MatchData>>>
//            ) {
//                if (!response.isSuccessful){
//                    listener.didError(response.message())
//                    return
//                }
//                response.body()?.let { listener.didFetch(response.message(), it) }
//            }
//
//            override fun onFailure(call: Call<ResponseWrapper<List<MatchData>>>, t: Throwable) {
//                t.message?.let { listener.didError(it) }
//            }
//
//        })
//    }


    interface ApiInterface {
//        var token: S
        @POST("api/v1/user")
        fun register(
            @Body
            body: RegisterRequest
        ):Call<ResponseWrapper<RegisterResponse>>

        @POST("api/v1/user/login")
        fun login(
            @Body
            body: LoginRequest
        ):Call<ResponseWrapper<LoginResponse>>

        @GET("api/v1/team")
        fun getAllTeamInfo(
            @Header("Authorization") token: String
        ):Call<ResponseWrapper<List<TeamInfo>>>

        @GET("api/v1/team/{id}")
        fun getTeamInfo(
            @Path("id") id: String
        ):Call<ResponseWrapper<TeamInfo>>

        @GET("api/v1/match")
        fun getAllMatchInfo(
            @Header("Authorization") token: String
        ):Call<ResponseWrapper<List<MatchData>>>

        @GET("api/v1/standings")
        fun getGroupStandings(
            @Header("Authorization") token: String
        ):Call<ResponseWrapper<List<StandingsResponse>>>
    }

}
