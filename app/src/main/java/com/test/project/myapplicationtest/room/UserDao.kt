package com.test.project.myapplicationtest.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

/**
 * Created by Harshal Chaudhari on 16/06/21.
 */


@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(identity: UserIdentity)

    @Query("select* from userData where userId = :id")
    fun getUserById(id: String): Flow<UserIdentity>

    @Query("select* from userData")
    fun getData(): Flow<List<UserIdentity>>
}