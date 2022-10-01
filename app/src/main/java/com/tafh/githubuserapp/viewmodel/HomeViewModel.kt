package com.tafh.githubuserapp.viewmodel

import android.util.Log
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

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _isEmpty = MutableLiveData<Boolean>()
    val isEmpty: LiveData<Boolean> = _isEmpty

    companion object{
        private const val TAG = "HomeViewModel"
    }

    fun querySearchUser(queryString: String) {
        _isLoading.value = true
        _isEmpty.value = false
        val apiService = RetrofitConfig.getApiService()
        val client = apiService.getSearchUser(queryString)
        client.enqueue(object : Callback<SearchUserResponse> {
            override fun onResponse(
                call: Call<SearchUserResponse>,
                response: Response<SearchUserResponse>
            ) {
                _isLoading.value = false
                if (!response.isSuccessful) {
                    Log.e(TAG, "onFailure: ${response.message()} tidak sukses")
                } else {
                    var data = response.body()!!.items
                    if (data.size.equals(0)) {
                        _isEmpty.value = true
                        Log.e(TAG, "data kosong")
                    } else {
                        _users.value = data
                        Log.e(TAG, "$data")
                    }
                }
            }

            override fun onFailure(call: Call<SearchUserResponse>, t: Throwable) {
                _isLoading.value = false
                _isEmpty.value = true
                Log.e(TAG, "onFailure: ${t.message.toString()} gagal")
            }

        })

    }




}