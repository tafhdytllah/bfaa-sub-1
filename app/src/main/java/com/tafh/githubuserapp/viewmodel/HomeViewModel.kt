package com.tafh.githubuserapp.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tafh.githubuserapp.api.RetrofitConfig
import com.tafh.githubuserapp.api.response.SearchUserResponse
import com.tafh.githubuserapp.data.model.UserItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel : ViewModel(){

    private val _searchUser = MutableLiveData<List<UserItem>>()
    val searchUser: LiveData<List<UserItem>> =_searchUser

    companion object {
        private const val query ="Taufik Hidayatullah"
    }


    init {
        findUser()
    }

    private fun findUser() {
        val apiService = RetrofitConfig.getApiService()
        val client = apiService.getSearchUser(query)
        client.enqueue(object : Callback<SearchUserResponse> {
            override fun onResponse(
                call: Call<SearchUserResponse>,
                response: Response<SearchUserResponse>
            ) {
                if (!response.isSuccessful) {

                } else {
                    _searchUser.value = response.body()?.items
                    Log.e("INFORMASI", searchUser.toString())
                }
            }

            override fun onFailure(call: Call<SearchUserResponse>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })

    }
}