package com.example.teervoetpuntjesapp.data.gebruiker

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.teervoetpuntjesapp.data.puntje.PuntjeEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface GebruikerPuntjeDao {

    @Query("select * from puntjes inner join gebruiker_puntje on puntjes.id == gebruiker_puntje.puntId where gebruiker_puntje.gebruikerId = :gebruikerId")
    fun getGebruikerPuntjes(gebruikerId: Int): Flow<List<Gebruiker_PuntjeEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addPuntje(gebruikersPuntjes: Gebruiker_PuntjeEntity)
}