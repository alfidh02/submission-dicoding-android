package com.project.submissiondicodinggithub3.api


import com.project.submissiondicodinggithub3.model.User
import com.project.submissiondicodinggithub3.model.UserArray
import com.project.submissiondicodinggithub3.model.UserDetail
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiEndPoint {
    @GET("search/users")
    @Headers("Authorization: token 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")
    fun getSearchUser(@Query("q") query: String): Call<UserArray>


    @GET("users/{username}")
    @Headers("Authorization: token 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")
    fun getDetailUser(
        @Path("username") username: String
    ): Call<UserDetail>


    @GET("users/{username}/followers")
    @Headers("Authorization: token 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")
    fun getFollowersUser(
        @Path("username") username: String
    ): Call<ArrayList<User>>


    @GET("users/{username}/following")
    @Headers("Authorization: token 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")
    fun getFollowingUser(
        @Path("username") username: String
    ): Call<ArrayList<User>>


}