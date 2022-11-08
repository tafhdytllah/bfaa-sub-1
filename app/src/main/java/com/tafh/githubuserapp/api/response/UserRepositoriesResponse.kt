package com.tafh.githubuserapp.api.response

import com.google.gson.annotations.SerializedName

data class UserRepositoriesResponse(
    @SerializedName("name")
    val name: String?,

    @SerializedName("visibility")
    val visibility: String?,

    @SerializedName("language")
    val language: String?,

    @SerializedName("updated_at")
    val updateAt: String?
)