package com.example.notesapp.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.notesapp.model.User

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addUser(user: User)

    @Update
    suspend fun updateUser(user: User)

    @Delete
    suspend fun deleteUser(user: User)

    @Query("DELETE FROM user_card")
    suspend fun deleteAllUsers()

    @Query("SELECT * FROM user_card ORDER BY id ASC")
    fun readAllData(): LiveData<List<User>>
}