package com.tafh.githubuserapp.api

import com.tafh.githubuserapp.api.response.DetailUserResponse
import com.tafh.githubuserapp.api.response.ListUserResponse
import com.tafh.githubuserapp.api.response.SearchUserResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    /**
     *
     * BASE_URL : https://api.github.com
     *
     * Endpoint
     * List Follower : /users/{username}/followers
     * List Following : /users/{username}/following
     *
     * my_token = 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
     */

    /**
     *
     * Search User
     * Endpoint : /search/users?q={username}
     *
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