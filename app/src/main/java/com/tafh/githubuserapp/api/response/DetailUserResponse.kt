package com.tafh.githubuserapp.api.response


import com.google.gson.annotations.SerializedName

data class DetailUserResponse(
    @SerializedName("avatar_url")
    val avatarUrl: String,
    @SerializedName("followers")
    val followers: Int,
    @SerializedName("following")
    val following: Int,
    @SerializedName("company")
    val company: String,
    @SerializedName("location")
    val location: String,
    @SerializedName("login")
    val login: String,
    @SerializedName("name")
    val name: String
)