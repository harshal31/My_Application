package com.test.project.myapplicationtest.room

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by Harshal Chaudhari on 16/06/21.
 */

@Entity(tableName = "userData")
data class UserIdentity(
    @PrimaryKey
    val userId: String = "",
    val name: String,
    val liked: Boolean = false
)