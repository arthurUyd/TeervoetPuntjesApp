package com.example.teervoetpuntjesapp.data.gebruiker

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface GebruikerDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(gebruiker: GebruikerEntity)

    @Query("select * from gebruikers where email = :email and password = :password")
    fun getGebruiker(email: String, password: String): Flow<GebruikerEntity>

    @Query("select * from gebruikers order by id asc")
    fun getAllGebruikers(): Flow<List<GebruikerEntity>>
}
