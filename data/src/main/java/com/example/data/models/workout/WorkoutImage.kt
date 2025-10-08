package com.example.data.models.workout

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class WorkoutImage(
    val id: String,
    val name: String,
    val url: String,
    val type: String,
    val size: String,
    val format: String,
    val source: String,
    @SerialName("content_type") val contentType: String,
    val archetype: String,
    val priority: Int,
    @SerialName("is_flipped") val isFlipped: Boolean,
    @SerialName("platform_id") val platformId: String,
    @SerialName("created_at") val createdAt: String,
    @SerialName("updated_at") val updatedAt: String
)