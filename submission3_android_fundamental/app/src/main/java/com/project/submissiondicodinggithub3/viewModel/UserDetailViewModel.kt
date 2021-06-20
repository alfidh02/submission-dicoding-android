package com.project.submissiondicodinggithub3.viewModel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.project.submissiondicodinggithub3.api.ApiClient
import com.project.submissiondicodinggithub3.model.UserDetail
import com.project.submissiondicodinggithub3.model.UserFavorite
import com.project.submissiondicodinggithub3.room.UserFavoriteDao
import com.project.submissiondicodinggithub3.room.UserFavoriteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Response

class UserDetailViewModel(application: Application) : AndroidViewModel(application) {
    val userDetail = MutableLiveData<UserDetail>()

    private var userFavoriteDB: UserFavoriteDatabase? = UserFavoriteDatabase.getDB(application)
    private var userFavoriteDao: UserFavoriteDao? = userFavoriteDB?.userFavoriteDao()

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

    fun addToFavList(username: String, id: Int, ava: String, type: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val gitUser = UserFavorite(username, id, ava, type)
            userFavoriteDao?.addUserFav(gitUser)
            Log.d("Failure", gitUser.toString())
        }
    }

    fun removeFromFavList(id: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            userFavoriteDao?.removeUserFav(id)
            Log.d("Failure", id.toString())
        }
    }

    suspend fun checkUserFavList(id: Int) = userFavoriteDao?.checkUserFav(id)
}