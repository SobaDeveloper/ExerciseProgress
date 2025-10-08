package com.example.data.models.workout

import kotlinx.datetime.Instant
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.util.UUID

@Serializable
enum class WorkoutCompletionState {
    @SerialName("none")
    NONE,
    @SerialName("partial")
    PARTIAL,
    @SerialName("full")
    FULL
}

@Serializable
data class WorkoutSummary(
    @SerialName("workout_id")
    val workoutID: String,
    
    @SerialName("id")
    val workoutSummaryID: String? = null,
    
    @SerialName("session_id")
    val sessionID: String? = null,
    
    @SerialName("started_at")
    val startedAt: Instant? = null,
    
    @SerialName("completed_at")
    val completedAt: Instant? = null,
    
    @SerialName("timed_out_at")
    val timedOutDate: Instant? = null,
    
    @SerialName("completion_state")
    val completionState: WorkoutCompletionState = WorkoutCompletionState.NONE,
    
    @SerialName("completed_automatically")
    val completedAutomatically: Boolean? = null,
    
    @SerialName("actual_duration")
    val actualDuration: Int? = null,
    
    @SerialName("active_energy_burned")
    val activeEnergyBurned: Int? = null,
    
    @SerialName("basal_energy_burned")
    val basalEnergyBurned: Int? = null,
    
    @SerialName("average_heart_rate")
    val averageHeartRate: Int? = null,
    
    @SerialName("max_heart_rate")
    val maxHeartRate: Int? = null,
    
    @SerialName("distance")
    val distance: Double? = null,
    
    @SerialName("difficulty")
    val difficulty: Float? = null,
    
    @SerialName("notes")
    val notes: String? = null,
    
    @SerialName("timezone")
    val timezone: String? = null,
    
    @SerialName("is_paused")
    val isPaused: Boolean = false,
    
    @SerialName("is_watch_present")
    val isWatchPresent: Boolean = false,
    
    @SerialName("set_summaries")
    val setSummaries: List<ExerciseSetSummary> = emptyList(),
    
    @SerialName("updated_at")
    val updatedDate: Instant? = null,
    
    @SerialName("unique_cache_id")
    val uniqueCacheID: String? = null
) {
    fun setSummariesFor(exerciseSetID: String): List<ExerciseSetSummary> {
        return setSummaries.filter { it.exerciseSetID == exerciseSetID }
    }
    
    val computedEndDate: Instant?
        get() {
            return if (timedOutDate != null) {
                startedAt
            } else {
                completedAt
            }
        }
    
    companion object {
        fun create(workoutID: String): WorkoutSummary {
            return WorkoutSummary(
                workoutID = workoutID,
                sessionID = UUID.randomUUID().toString().lowercase(),
                completionState = WorkoutCompletionState.NONE
            )
        }
    }
}

fun List<WorkoutSummary>.sortedByStartedAtDate(): List<WorkoutSummary> {
    return sortedBy { it.startedAt }
}