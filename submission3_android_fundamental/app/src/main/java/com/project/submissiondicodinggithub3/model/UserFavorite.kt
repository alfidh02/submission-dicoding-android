package com.project.submissiondicodinggithub3.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "favorite_user")
data class UserFavorite(
    val login: String,
    @PrimaryKey
    val id: Int,
    val avatar_url: String,
    val type: String
): Serializable
