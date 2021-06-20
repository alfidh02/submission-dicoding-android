package com.project.consumerfavuser.db

import android.database.Cursor
import com.project.consumerfavuser.model.User

object MapHelper {
    fun mapCursorToArrayList(dataCursor: Cursor?): ArrayList<User> {
        val favList = ArrayList<User>()

        dataCursor?.apply {
            while (moveToNext()) {
                val username = getString(getColumnIndexOrThrow(DBContract.Column.USERNAME))
                val idUser = getInt(getColumnIndexOrThrow(DBContract.Column.ID))
                val avatar = getString(getColumnIndexOrThrow(DBContract.Column.AVATAR_URL))
                val type = getString(getColumnIndexOrThrow(DBContract.Column.TYPE))
                favList.add(User(username, idUser, avatar, type))
            }
        }
        return favList
    }
}