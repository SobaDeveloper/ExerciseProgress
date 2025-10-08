package com.example.domain.usecase.workout

import com.example.data.models.workout.Workout
import com.example.domain.repo.workout.WorkoutRepository
import javax.inject.Inject

class GetWorkouts @Inject constructor(
    private val repository: WorkoutRepository
) {
    suspend operator fun invoke(): List<Workout> = repository.getWorkouts()
}