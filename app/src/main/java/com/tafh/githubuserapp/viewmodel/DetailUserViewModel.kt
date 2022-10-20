package com.tafh.githubuserapp.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.tafh.githubuserapp.api.RetrofitConfig
import com.tafh.githubuserapp.api.response.DetailUserResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private const val TAG = "DetailUserViewModel"

class DetailUserViewModel : ViewModel() {

    fun getDetailUser(username: String) {
        val apiService = RetrofitConfig.getApiService()
        val client = apiService.getDetailUser(username)

        client.enqueue(object : Callback<DetailUserResponse> {
            override fun onResponse(
                call: Call<DetailUserResponse>,
                response: Response<DetailUserResponse>
            ) {
                if (!response.isSuccessful) {
                    Log.d(TAG, "getDetailUser: tidak sukses : ${response.message()} ")
                } else {
                    Log.d(TAG, "getDetailUser: sukses : ${response.body()}")
                }
            }

            override fun onFailure(call: Call<DetailUserResponse>, t: Throwable) {
                Log.d(TAG, "getDetailUser: onFailure : ${t.message} ")
            }

        })


    }
}