package com.example.data.models.workout

import kotlinx.datetime.Instant
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.util.UUID

@Serializable
enum class ExerciseSetCompletionState {
    @SerialName("none")
    NONE,
    @SerialName("partial")
    PARTIAL,
    @SerialName("full")
    FULL
}

@Serializable
data class ExerciseSetSummary(
    @SerialName("id")
    val id: String? = null,
    
    @SerialName("set_id")
    val exerciseSetID: String,
    
    @SerialName("summary_id")
    val workoutSummaryID: String? = null,
    
    @SerialName("session_id")
    val sessionID: String? = null,
    
    @SerialName("started_at")
    val startedAt: Instant? = null,
    
    @SerialName("completed_at")
    val completedAt: Instant? = null,
    
    @SerialName("completion_state")
    val completionState: ExerciseSetCompletionState = ExerciseSetCompletionState.NONE,
    
    @SerialName("time_spent_active")
    val timeSpentActive: Int? = null,
    
    @SerialName("completed_automatically")
    val completedAutomatically: Boolean? = null,
    
    @SerialName("weight")
    val weight: Float? = null,
    
    @SerialName("user_adjusted_weight")
    val userAdjustedWeight: Boolean? = null,
    
    @SerialName("reps_counted")
    val repsCounted: Int? = null,
    
    @SerialName("reps_reported")
    val repsReported: Int? = null,
    
    @SerialName("unique_cache_id")
    val uniqueCacheID: String? = null,
    
    @SerialName("set")
    val exerciseSet: ExerciseSet? = null,
    
    @SerialName("motion_data_path")
    val motionDataPath: String? = null
) {
    companion object {
        fun create(exerciseSetID: String, workoutID: String): ExerciseSetSummary {
            return ExerciseSetSummary(
                exerciseSetID = exerciseSetID,
                sessionID = UUID.randomUUID().toString().lowercase(),
                completionState = ExerciseSetCompletionState.NONE
            )
        }
    }
}

fun List<ExerciseSetSummary>.highestCompletionState(): ExerciseSetCompletionState {
    return when {
        any { it.completionState == ExerciseSetCompletionState.FULL } -> ExerciseSetCompletionState.FULL
        any { it.completionState == ExerciseSetCompletionState.PARTIAL } -> ExerciseSetCompletionState.PARTIAL
        else -> ExerciseSetCompletionState.NONE
    }
}

fun List<ExerciseSetSummary>.lastReportedWeight(): Float? {
    return mapNotNull { it.weight }.lastOrNull()
}

fun List<ExerciseSetSummary>.lastRecordedRepCount(): Int? {
    return mapNotNull { it.repsReported ?: it.repsCounted }.lastOrNull()
}