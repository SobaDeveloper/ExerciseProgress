package com.example.domain.repo.workout

import com.example.data.datasource.workout.WorkoutsLocalSource
import com.example.data.models.workout.Workout
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WorkoutRepository @Inject constructor(
    private val localDataSource: WorkoutsLocalSource
) {
    suspend fun getWorkouts(): List<Workout> = localDataSource.getWorkouts()
}