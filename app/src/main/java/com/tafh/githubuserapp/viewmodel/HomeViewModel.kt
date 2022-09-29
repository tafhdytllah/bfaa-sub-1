package com.tafh.githubuserapp.viewmodel

import androidx.lifecycle.*
import com.tafh.githubuserapp.api.RetrofitConfig
import com.tafh.githubuserapp.api.response.SearchUserResponse
import com.tafh.githubuserapp.data.model.UserItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel : ViewModel(){

    private val _users = MutableLiveData<List<UserItem>>()
    val users: LiveData<List<UserItem>> =_users

    fun querySearchUser(queryString: String) {
        val apiService = RetrofitConfig.getApiService()
        val client = apiService.getSearchUser(queryString)
        client.enqueue(object : Callback<SearchUserResponse> {
            override fun onResponse(
                call: Call<SearchUserResponse>,
                response: Response<SearchUserResponse>
            ) {
                if (!response.isSuccessful) {

                } else {
                    _users.value = response.body()?.items
                }
            }

            override fun onFailure(call: Call<SearchUserResponse>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })

    }




}