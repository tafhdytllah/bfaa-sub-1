package com.tafh.githubuserapp.data.model


import com.google.gson.annotations.SerializedName

data class UserItem(
    @SerializedName("avatar_url")
    val avatarUrl: String,
    @SerializedName("html_url")
    val htmlUrl: String,
    @SerializedName("login")
    val login: String
)