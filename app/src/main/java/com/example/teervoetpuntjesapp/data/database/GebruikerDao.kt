package com.example.teervoetpuntjesapp.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface GebruikerDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: dbGebruiker)

    @Update
    suspend fun update(item: dbGebruiker)

    @Delete
    suspend fun delete(item: dbGebruiker)

    @Query("select * from gebruiker where email = :email")
    fun getGebruiker(email: String): Flow<dbGebruiker>

    @Query("select * from gebruiker")
    fun getAllGebruikers(): Flow<List<dbGebruiker>>
}