package com.project.submissiondicodinggithub3.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.project.submissiondicodinggithub3.model.UserFavorite
import com.project.submissiondicodinggithub3.room.UserFavoriteDao
import com.project.submissiondicodinggithub3.room.UserFavoriteDatabase

class FavoriteViewModel(app: Application) : AndroidViewModel(app) {
    private var userFavoriteDao: UserFavoriteDao?
    private var userFavoriteDB: UserFavoriteDatabase? = UserFavoriteDatabase.getDB(app)

    init {
        userFavoriteDao = userFavoriteDB?.userFavoriteDao()
    }

    fun getListFavUser(): LiveData<List<UserFavorite>>? {
        return userFavoriteDao?.getUserFav()
    }
}