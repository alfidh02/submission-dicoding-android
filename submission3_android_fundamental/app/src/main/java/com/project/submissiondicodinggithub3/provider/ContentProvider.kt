package com.project.submissiondicodinggithub3.provider

import android.content.ContentProvider
import android.content.ContentValues
import android.content.UriMatcher
import android.database.Cursor
import android.net.Uri
import com.project.submissiondicodinggithub3.room.UserFavoriteDao
import com.project.submissiondicodinggithub3.room.UserFavoriteDatabase

class ContentProvider : ContentProvider() {

    companion object {
        private const val AUTHORITY = "com.project.submissiondicodinggithub3"
        private const val ID_DATA = 1
        private const val TABLE_NAME = "favorite_user"

        private val uriMatcher = UriMatcher(UriMatcher.NO_MATCH)

        init {
            uriMatcher.addURI(AUTHORITY, TABLE_NAME, ID_DATA)
        }
    }

    private lateinit var userFavoriteDao: UserFavoriteDao

    override fun onCreate(): Boolean {
        val let = context?.let {
            UserFavoriteDatabase.getDB(it)?.userFavoriteDao()
        }
        userFavoriteDao = if (let != null) let else throw KotlinNullPointerException()
        return false
    }

    override fun query(
        uri: Uri, projection: Array<out String>?, selection: String?,
        selectionArgs: Array<out String>?, sortOrder: String?,
    ): Cursor? {
        val cursor: Cursor?
        when (uriMatcher.match(uri)) {
            ID_DATA -> {
                cursor = userFavoriteDao.findUserFav()
                if (context != null) {
                    cursor.setNotificationUri(context?.contentResolver, uri)
                }
            }
            else -> {
                cursor = null
            }
        }
        return cursor
    }

    override fun getType(uri: Uri): String? = null

    override fun insert(uri: Uri, values: ContentValues?): Uri? = null

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<out String>?): Int = 0

    override fun update(
        uri: Uri, values: ContentValues?, selection: String?,
        selectionArgs: Array<out String>?,
    ): Int = 0
}