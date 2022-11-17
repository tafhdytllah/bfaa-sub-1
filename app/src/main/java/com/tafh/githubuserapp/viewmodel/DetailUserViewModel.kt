package com.tafh.githubuserapp.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tafh.githubuserapp.data.remote.api.RetrofitConfig
import com.tafh.githubuserapp.data.remote.response.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailUserViewModel : ViewModel() {

    companion object {
        private const val TAG = "DetailUserViewModel"
    }

    private val _userDetail = MutableLiveData<UserResponse>()
    val userDetail: LiveData<UserResponse> = _userDetail

    private val _userFollower = MutableLiveData<UserFollowerResponse>()
    val userFollower: LiveData<UserFollowerResponse> = _userFollower

    private val _userRepo = MutableLiveData<List<Repository>>()
    val userRepo: LiveData<List<Repository>> = _userRepo

    private val _userFollowing = MutableLiveData<UserFollowingResponse>()
    val userFollowing: LiveData<UserFollowingResponse> = _userFollowing

    private val apiService = RetrofitConfig.getApiService()

    fun getUserFollowing(username: String) {
        val apiClient = apiService.getUserFollowing(username)

        apiClient.enqueue(object : Callback<UserFollowingResponse> {
            override fun onResponse(
                call: Call<UserFollowingResponse>,
                response: Response<UserFollowingResponse>
            ) {
                if (!response.isSuccessful) {
                    Log.e(TAG, "onFailure: ${response.body()} tidak sukses")
                } else {
//                    Log.e(TAG, "onResponse: ${response.body()}" )
                    _userFollowing.value = response.body()
                }
            }

            override fun onFailure(call: Call<UserFollowingResponse>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message.toString()} gagal")
            }

        })
    }

    fun getUserRepo(username: String) {
        val apiClient = apiService.getUserRepositories(username)

        apiClient.enqueue(object : Callback<UserRepositoriesResponse> {
            override fun onResponse(
                call: Call<UserRepositoriesResponse>,
                response: Response<UserRepositoriesResponse>
            ) {
                if (!response.isSuccessful) {
                    Log.e(TAG, "onFailure: ${response.body()} tidak sukses")
                } else {
                    _userRepo.value = response.body()
                }
            }

            override fun onFailure(call: Call<UserRepositoriesResponse>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message.toString()} gagal")
            }

        })
    }

    fun getUserFollower(username: String) {
        val apiClient = apiService.getUserFollowers(username)

        apiClient.enqueue(object : Callback<UserFollowerResponse> {
            override fun onResponse(
                call: Call<UserFollowerResponse>,
                response: Response<UserFollowerResponse>
            ) {
                if (!response.isSuccessful) {
                    Log.e(TAG, "onFailure: ${response.body()} tidak sukses")
                } else {
//                    Log.e(TAG, "onResponse: ${response.body()}" )
                    _userFollower.value = response.body()
                }
            }

            override fun onFailure(call: Call<UserFollowerResponse>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message.toString()} gagal")
            }

        })
    }

    fun getDetailUser(username: String) {
        val apiService = RetrofitConfig.getApiService()
        val client = apiService.getDetailUser(username)

        client.enqueue(object : Callback<UserResponse> {
            override fun onResponse(
                call: Call<UserResponse>,
                response: Response<UserResponse>
            ) {
                if (!response.isSuccessful) {
                    Log.d(TAG, "getDetailUser not found : ${response.message()} ")
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