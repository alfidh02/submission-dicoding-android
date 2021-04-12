package com.project.submissiondicodinggithub2.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.project.submissiondicodinggithub2.api.ApiClient
import com.project.submissiondicodinggithub2.model.User
import retrofit2.Call
import retrofit2.Response
import retrofit2.Callback

class FollowingViewModel : ViewModel() {
    val listFollowing = MutableLiveData<ArrayList<User>>()

    fun setListFollowing(username: String) {
        ApiClient.apiRequest
            .getFollowingUser(username)
            .enqueue(object : Callback<ArrayList<User>> {
                override fun onResponse(
                    call: Call<ArrayList<User>>,
                    response: Response<ArrayList<User>>
                ) {
                    if (response.isSuccessful) {
                        listFollowing.postValue(response.body())
                    }
                }

                override fun onFailure(call: Call<ArrayList<User>>, error: Throwable) {
                    Log.d("Failure", error.message.toString())
                }

            })
    }

    fun getListFollowing(): LiveData<ArrayList<User>> = listFollowing
}