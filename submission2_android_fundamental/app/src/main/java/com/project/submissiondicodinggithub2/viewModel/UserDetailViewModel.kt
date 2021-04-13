package com.project.submissiondicodinggithub2.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.project.submissiondicodinggithub2.api.ApiClient
import com.project.submissiondicodinggithub2.model.UserDetail
import retrofit2.Call
import retrofit2.Response

class UserDetailViewModel : ViewModel() {
    val userDetail = MutableLiveData<UserDetail>()

    fun setUserDetail(username: String) {
        ApiClient.apiRequest
                .getDetailUser(username)
                .enqueue(object : retrofit2.Callback<UserDetail> {
                    override fun onResponse(call: Call<UserDetail>, response: Response<UserDetail>) {
                        if (response.isSuccessful) {
                            userDetail.postValue(response.body())
                        }
                    }

                    override fun onFailure(call: Call<UserDetail>, error: Throwable) {
                        Log.d("Failure", error.message.toString())
                    }

                })
    }

    fun getUserDetail(): LiveData<UserDetail> = userDetail

}