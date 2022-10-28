package com.tafh.githubuserapp.api

import com.tafh.githubuserapp.api.response.DetailUserResponse
import com.tafh.githubuserapp.api.response.SearchUserResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    /**
     *
     * Endpoint
     * List Follower :
     * List Following : /users/{username}/following
     *
     */

    /**
     *
     * Base URL             : https://api.github.com
     *
     * Endpoint
     * Search User by query : /search/users?q={username}
     * Detail user          : /users/{username}
     * List Follower        : /users/{username}/followers
     * List Following       : /users/{username}/following
     */
    @GET("search/users")
    @Headers("Authorization: Bearer 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")
    fun getSearchUser(
        @Query("q") q: String
    ): Call<SearchUserResponse>

    /**
     *
     * Detail User
     * Endpoint : /users/{username}
     *
     */
    @GET("users/{username}")
    @Headers("Authorization: Bearer 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")
    fun getDetailUser(
        @Path("username") username: String
    ): Call<DetailUserResponse>


}