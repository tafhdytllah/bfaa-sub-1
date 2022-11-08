package com.tafh.githubuserapp.api.response

import com.google.gson.annotations.SerializedName
import com.tafh.githubuserapp.data.model.UserItem

data class ListUsersResponse(
    @SerializedName("items")
    val items: List<UserItem>,
    @SerializedName("total_count")
    val totalCount: Int
)