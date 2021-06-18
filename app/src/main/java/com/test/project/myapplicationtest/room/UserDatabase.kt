package com.test.project.myapplicationtest.room

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

/**
 * Created by Harshal Chaudhari on 16/06/21.
 */

@Database(entities = [UserIdentity::class], version = 3)
abstract class UserDatabase : RoomDatabase() {

    abstract fun getUserDao(): UserDao

    companion object {

        @Volatile
        private var instance: UserDatabase? = null

        @Synchronized
        fun getInstance(application: Application): UserDatabase {
            if (instance == null) {
                instance = Room.databaseBuilder(application, UserDatabase::class.java, "user_database")
                    .fallbackToDestructiveMigration()
                    .build()
            }

            return instance!!
        }
    }

}