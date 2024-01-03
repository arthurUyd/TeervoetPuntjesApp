package com.example.teervoetpuntjesapp.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface PuntjeDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: dbPuntjes)

    @Update
    suspend fun update(item: dbPuntjes)

    @Delete
    suspend fun delete(item: dbPuntjes)

    @Query("select * from puntjes where titel = :titel")
    fun getPuntje(titel: String): Flow<dbPuntjes>

    @Query("select * from puntjes")
    fun getAllPuntjes(): Flow<List<dbPuntjes>>
}
