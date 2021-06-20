package com.project.submissiondicodinggithub3.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.project.submissiondicodinggithub3.api.ApiClient
import com.project.submissiondicodinggithub3.model.User
import com.project.submissiondicodinggithub3.model.UserArray
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserViewModel : ViewModel() {
    val mutableListUser = MutableLiveData<ArrayList<User>>()

    fun setUserSearch(query: String) {
        ApiClient.apiRequest.getSearchUser(query).enqueue(object : Callback<UserArray> {
            override fun onResponse(call: Call<UserArray>, response: Response<UserArray>) {
                if (response.isSuccessful) {
                    mutableListUser.postValue(response.body()?.items)
                }
            }

            override fun onFailure(call: Call<UserArray>, error: Throwable) {
                Log.d("Failure", error.message.toString())
            }

        })
    }

    fun getUserSearch(): LiveData<ArrayList<User>> = mutableListUser
}