package com.example.teervoetpuntjesapp.data.puntje

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.teervoetpuntjesapp.Model.Badge
import com.example.teervoetpuntjesapp.Model.Puntje
import kotlinx.coroutines.flow.Flow

@Dao
interface PuntjeDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(puntje: List<PuntjeEntity>)

    @Update
    suspend fun update(puntje: PuntjeEntity)

    @Delete
    suspend fun delete(puntje: PuntjeEntity)

    @Query("select * from puntjes where badge_id= :badge_id")
    fun getPuntjesVoorBadge(badge_id: Int): Flow<List<PuntjeEntity>>

    @Query("select * from puntjes order by id desc")
    fun getAllPuntjes(): Flow<List<PuntjeEntity>>

}