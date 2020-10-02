package com.example.weatherapp.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface UserDao {

    @Insert
    fun insert(user: User)

    @Insert
    @JvmSuppressWildcards
    fun insertAll(users: List<User>)

    @Query("SELECT * FROM user ")
    fun getAll(): List<User>

    @Query("SELECT * FROM user WHERE name = (:userName)")
    fun getByName(userName: String): User

    @Query("DELETE FROM user")
    fun deleteAll()

    @Query("SELECT count(*) FROM user ")
    fun getCount(): Int

}