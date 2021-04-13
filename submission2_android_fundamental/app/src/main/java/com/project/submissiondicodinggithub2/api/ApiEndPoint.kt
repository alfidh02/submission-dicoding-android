package com.project.submissiondicodinggithub2.api


import com.project.submissiondicodinggithub2.model.User
import com.project.submissiondicodinggithub2.model.UserArray
import com.project.submissiondicodinggithub2.model.UserDetail
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