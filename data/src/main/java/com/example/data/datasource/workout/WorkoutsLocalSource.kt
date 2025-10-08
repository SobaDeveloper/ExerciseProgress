package com.example.data.datasource.workout

import android.content.Context
import com.example.core.utils.extensions.nilUUIDString
import com.example.data.models.workout.Workout
import com.example.data.models.workout.WorkoutSummary
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json

@Singleton
class WorkoutsLocalSource @Inject constructor(
    @ApplicationContext private val context: Context
) {

    private val json = Json {
        ignoreUnknownKeys = true
        isLenient = true
        coerceInputValues = true
    }

    suspend fun getWorkouts(): List<Workout> = withContext(Dispatchers.IO) {
        try {
            val workoutsJson = context.assets.open("workouts.json")
                .bufferedReader()
                .use { it.readText() }

            val shallowWorkouts = json.decodeFromString<List<Workout>>(workoutsJson)
            val allSummaries = loadWorkoutSummaries(shallowWorkouts)

            shallowWorkouts.map { workout ->
                val summaries = allSummaries.filter { it.workoutID == workout.id }
                workout.copy(summaries = summaries)
            }
        } catch (e: Exception) {
            println("Failed to load workouts: ${e.message}")
            emptyList()
        }
    }

    private fun loadWorkoutSummaries(shallowWorkouts: List<Workout>): List<WorkoutSummary> {
        val summaries = mutableListOf<WorkoutSummary>()

        val summaryIDs = shallowWorkouts
            .flatMap { it.summaries }
            .mapNotNull { it.workoutSummaryID }

        for (id in summaryIDs) {
            try {
                val summaryJson = context.assets.open("summaries/$id.json")
                    .bufferedReader()
                    .use { it.readText() }
                val summary = json.decodeFromString<WorkoutSummary>(summaryJson)
                val filtered = summary.copy(
                    setSummaries = summary.setSummaries.filter { it.exerciseSet?.id != nilUUIDString }
                )
                summaries.add(filtered)
            } catch (e: Exception) {
                println("Failed to load workout summary $id: ${e.message}")
            }
        }

        return summaries
    }
}