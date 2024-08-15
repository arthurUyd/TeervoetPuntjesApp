package com.example.teervoetpuntjesapp.Model

import com.example.teervoetpuntjesapp.data.badge.BadgeEntity
import kotlinx.serialization.Serializable

@Serializable
data class Badge(
    val id: Int = 0,
    val titel: String = "",
    val image_url: String = "",
) {
    companion object {
        fun asBadgeEntity(badge: Badge): BadgeEntity {
            return BadgeEntity(
                badge.id,
                badge.titel,
                badge.image_url,
            )
        }
    }
}
