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
     * List user
     */
    @Headers("Authorization: Bearer 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")
    @GET("users/{username}")
    fun getDetailUser(
        @Path("username") username: String
    ): Call<DetailUserResponse>

    /**
     * List user
     */
    @Headers("Authorization: Bearer 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")
    @GET("users")
    fun  getListUser() : Call<ListUserResponse>

    /**
     * Search User by query
     */
    @Headers("Authorization: Bearer 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")
    @GET("search/users")
    fun getSearchUser(
        @Query("q") q: String
    ): Call<SearchUserResponse>
}