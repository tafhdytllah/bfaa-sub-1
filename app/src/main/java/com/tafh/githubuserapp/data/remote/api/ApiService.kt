package com.tafh.githubuserapp.data.remote.api

import com.tafh.githubuserapp.data.remote.response.*
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    /**
     *  TASK :
     *  1. Menampilkan fragment List Follower & List Following yang diambil dari API.
     *  2. Menggunakan TabLayout, BottomNavigationView, atau yang lainnya sebagai navigasi antara halaman List Follower dan List Following.
     *  3. Aplikasi bisa menjaga data yang sudah dimuat ketika terjadi pergantian orientasi dari portrait ke landscape atau sebaliknya.
     *  4. Retrofit (ok)
     *  5. Menerapkan Android Architecture Component (minimal ViewModel dan LiveData) dengan benar. (ok)
     */

    /**
     *
     * Base URL             : https://api.github.com/
     * Endpoint
     * Search users                    : search/users?q={username}
     * Get a user                      : users/{username}
     * List repositories for a user    : users/{username}/repos
     * List followers of a user        : users/{username}/followers
     * List the people a user follows  : users/{username}/following
     *
     */

    @GET("search/users")
    @Headers("Authorization: Bearer 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")
    fun getSearchUser(
        @Query("q") q: String
    ): Call<SearchUserResponse>

    @GET("users/{username}")
    @Headers("Authorization: Bearer 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")
    fun getDetailUser(
        @Path("username") username: String
    ): Call<UserResponse>

    @GET("users/{username}/repos")
    @Headers("Authorization: Bearer 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")
    fun getUserRepositories(
        @Path("username") username: String
    ): Call<UserRepositoriesResponse>

    @GET("users/{username}/followers")
    @Headers("Authorization: Bearer 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")
    fun getUserFollowers(
        @Path("username") username: String
    ): Call<UserFollowerResponse>

    @GET("users/{username}/following")
    @Headers("Authorization: Bearer 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")
    fun getUserFollowing(
        @Path("username") username: String
    ): Call<UserFollowingResponse>


    /**
     *
    "name": "anime-movie-app"
    "visibility": "public"
    "language": "Kotlin"
    "updated_at": "2021-10-16T16:10:57Z"
     */


}