 package com.project.submissiondicodinggithub3.room

import android.database.Cursor
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.project.submissiondicodinggithub3.model.UserFavorite

@Dao
interface UserFavoriteDao {
    @Insert
    suspend fun addUserFav(userFav: UserFavorite)

    @Query("SELECT count(*) FROM favorite_user WHERE favorite_user.id = :id")
    suspend fun checkUserFav(id: Int) : Int

    @Query("DELETE FROM favorite_user WHERE favorite_user.id = :id")
    suspend fun removeUserFav(id: Int) : Int

    @Query("SELECT * FROM favorite_user")
    fun getUserFav(): LiveData<List<UserFavorite>>

    @Query("SELECT * FROM favorite_user")
    fun findUserFav(): Cursor
}