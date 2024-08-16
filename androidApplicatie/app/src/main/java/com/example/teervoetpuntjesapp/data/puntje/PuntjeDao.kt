package com.example.teervoetpuntjesapp.data.puntje

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

/**
 * Data Access Object interface voor puntjes in de Teervoetpuntjes applicatie.
 */
@Dao
interface PuntjeDao {

    /**
     * Voegt een lijst van PuntjeEntity objecten toe aan de Room database.
     *
     * @param puntjes De lijst van PuntjeEntity objecten die toegevoegd moeten worden.
     */
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(puntjes: List<PuntjeEntity>)

    /**
     * Haalt alle puntjes voor een specifieke badge op uit de Room database.
     *
     * @param badge_id Het ID van de badge waarvoor de puntjes opgehaald moeten worden.
     * @return Flow<List<PuntjeEntity>> Een Flow van een lijst met PuntjeEntity objecten.
     */
    @Query("select * from puntjes where badge_id= :badge_id")
    fun getPuntjesVoorBadge(badge_id: Int): Flow<List<PuntjeEntity>>

    /**
     * Haalt alle puntjes op uit de Room database.
     *
     * @return Flow<List<PuntjeEntity>> Een Flow van een lijst met PuntjeEntity objecten.
     */
    @Query("select * from puntjes order by id asc")
    fun getAllPuntjes(): Flow<List<PuntjeEntity>>
}