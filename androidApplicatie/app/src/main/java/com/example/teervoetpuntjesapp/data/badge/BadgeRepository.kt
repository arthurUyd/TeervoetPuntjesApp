package com.example.teervoetpuntjesapp.data.badge

import com.example.teervoetpuntjesapp.Model.Badge
import kotlinx.coroutines.flow.Flow

interface BadgeRepository {
    /**
     * Haalt een lijst van alle badges op als een Flow.
     *
     * @return Een Flow van een lijst met Badge objecten.
     */
    fun getBadges(): Flow<List<Badge>>

    /**
     * Haalt een specifieke badge op basis van de gegeven ID.
     *
     * @param id De ID van de badge die opgehaald moet worden.
     * @return Een Flow van een Badge object.
     */
    fun getBadge(id: Int): Flow<Badge>

    /**
     * Vernieuwt de lijst met badges.
     *
     * Deze functie wordt gebruikt om de lijst met badges te vernieuwen, dit indien er geen badges in de room database gevonden zijn.
     */
    suspend fun refreshBadges()
}