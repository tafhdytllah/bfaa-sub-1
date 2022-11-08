package com.tafh.githubuserapp.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tafh.githubuserapp.api.RetrofitConfig
import com.tafh.githubuserapp.api.response.UserResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private const val TAG = "DetailUserViewModel"

class DetailUserViewModel : ViewModel() {

    private val _userDetail = MutableLiveData<UserResponse>()
    val userDetail: LiveData<UserResponse> = _userDetail

    fun getDetailUser(username: String) {
        val apiService = RetrofitConfig.getApiService()
        val client = apiService.getDetailUser(username)

        client.enqueue(object : Callback<UserResponse> {
            override fun onResponse(
                call: Call<UserResponse>,
                response: Response<UserResponse>
            ) {
                if (!response.isSuccessful) {
                    Log.d(TAG, "getDetailUser onFailure : ${response.message()} ")
                } else {
                    Log.d(TAG, "getDetailUser success : ${response.body()}")

                    val data = response.body()!!
                    _userDetail.value = data

                }
            }

            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                Log.d(TAG, "getDetailUser onFailure : ${t.message} ")
            }

        })


    }
}