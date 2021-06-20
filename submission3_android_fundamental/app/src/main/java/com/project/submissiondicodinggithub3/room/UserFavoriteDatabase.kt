package com.project.submissiondicodinggithub3.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.project.submissiondicodinggithub3.model.UserFavorite

@Database(
    entities = [UserFavorite::class],
    version = 1
)

abstract class UserFavoriteDatabase : RoomDatabase() {
    abstract fun userFavoriteDao(): UserFavoriteDao

    companion object {
        @Volatile
        private var INSTANCE: UserFavoriteDatabase? = null

        fun getDB(mContext: Context): UserFavoriteDatabase? {
            if (INSTANCE == null) {
                synchronized(UserFavoriteDatabase::class) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(
                            mContext.applicationContext,
                            UserFavoriteDatabase::class.java, "database_user"
                        ).build()
                    }
                }
            }
            return INSTANCE
        }
    }

}