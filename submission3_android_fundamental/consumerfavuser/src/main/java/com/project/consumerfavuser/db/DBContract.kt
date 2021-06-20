package com.project.consumerfavuser.db

import android.net.Uri
import android.provider.BaseColumns

object DBContract {
    const val AUTHORITY = "com.project.submissiondicodinggithub3"
    const val SCHEME = "content"

    internal class Column: BaseColumns {
        companion object {
            private const val TABLE_NAME = "favorite_user"
            const val USERNAME = "login"
            const val ID = "id"
            const val AVATAR_URL = "avatar_url"
            const val TYPE = "type"

            val CONTENT_URI: Uri = Uri.Builder().scheme(SCHEME)
                .authority(AUTHORITY)
                .appendPath(TABLE_NAME)
                .build()
        }
    }
}