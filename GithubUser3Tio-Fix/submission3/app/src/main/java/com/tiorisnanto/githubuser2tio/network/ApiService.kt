package com.tiorisnanto.githubuser2tio.network

import com.tiorisnanto.githubuser2tio.model.ResponseSearch
import com.tiorisnanto.githubuser2tio.model.ResponseUser
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("search/users")
    @Headers("Authorization: token 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")
    fun getSearchUser(
        @Query("q") q: String
    ): Call<ResponseSearch>


    @GET("users/{username}")
    @Headers("Authorization: token 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")
    fun getDetailUser(
        @Path("username") username: String
    ): Call<ResponseUser>

    @GET("users/{username}/followers")
    @Headers("Authorization: token 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")
    fun getUserFollower(
        @Path("username") username: String
    ): Call<ArrayList<ResponseUser>>

    @GET("users/{username}/following")
    @Headers("Authorization: token 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")
    fun getUserFollowing(
        @Path("username") username: String
    ): Call<ArrayList<ResponseUser>>
}