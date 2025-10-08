package com.example.data.utils

import com.example.data.models.workout.Exercise
import com.example.data.models.workout.ExerciseSet
import com.example.data.models.workout.ExerciseSetCompletionState
import com.example.data.models.workout.ExerciseSetSummary
import com.example.data.models.workout.ExerciseSetType
import com.example.data.models.workout.Workout
import com.example.data.models.workout.WorkoutCompletionState
import com.example.data.models.workout.WorkoutImage
import com.example.data.models.workout.WorkoutSummary
import kotlinx.datetime.Instant

object MockData {

    val mockWorkoutImage = WorkoutImage(
        id = "2d47bbe2-8c85-4c06-bc09-d4bf946f7d9d",
        name = "hero_14",
        url = "https://s3.amazonaws.com/assets.beta.future.fit/hero_images/hero_14.jpg",
        type = "workout",
        size = "l",
        format = "landscape",
        source = "beta",
        contentType = "",
        archetype = "",
        priority = 0,
        isFlipped = false,
        platformId = "09fe505c-622c-4097-96bd-2c1dca4c47d2",
        createdAt = "0001-01-01T00:00:00Z",
        updatedAt = "2020-12-02T18:11:27.043159Z"
    )

    val mockExerciseSetSummaries: List<ExerciseSetSummary> = listOf(
        ExerciseSetSummary(
            id = "1",
            exerciseSetID = "set1",
            workoutSummaryID = "workout1",
            sessionID = "session1",
            startedAt = Instant.parse("2020-12-01T22:07:49Z"),
            completedAt = Instant.parse("2020-12-01T22:08:32Z"),
            completionState = ExerciseSetCompletionState.FULL,
            timeSpentActive = 44,
            completedAutomatically = false,
            weight = 300.0f,
            exerciseSet = ExerciseSet(
                id = "set1",
                exercise = Exercise(
                    id = "ex1",
                    name = "Barbell Back Squat",
                    description = "Squat with barbell on back",
                    equipmentRequired = "Barbell",
                    isReps = true,
                    isWeight = true,
                    muscleGroups = "Hamstrings,Glutes"
                ),
                type = ExerciseSetType.REPS,
                reps = 3,
                weight = 300.0f
            )
        ),
        ExerciseSetSummary(
            id = "2",
            exerciseSetID = "set2",
            workoutSummaryID = "workout1",
            sessionID = "session2",
            startedAt = Instant.parse("2020-12-01T22:41:16Z"),
            completedAt = Instant.parse("2020-12-01T22:41:50Z"),
            completionState = ExerciseSetCompletionState.FULL,
            timeSpentActive = 34,
            completedAutomatically = false,
            weight = 25.0f,
            exerciseSet = ExerciseSet(
                id = "set2",
                exercise = Exercise(
                    id = "ex2",
                    name = "Dumbbell Tricep Extension",
                    description = "Lower dumbbells behind your head",
                    equipmentRequired = "Dumbbell,Bench",
                    isReps = true,
                    isWeight = true,
                    muscleGroups = "Triceps"
                ),
                type = ExerciseSetType.REPS,
                reps = 10,
                weight = 25.0f
            )
        )
    )

    val mockWorkoutSummary = WorkoutSummary(
        workoutID = "3a6fe022-e10e-4278-8998-2bb107f230d1",
        workoutSummaryID = "13c45daf-9aec-459c-bb94-f62e69d297fd",
        sessionID = "9eb9dfc3-2431-43f5-9fa2-f34dfb384f0e",
        startedAt = Instant.parse("2020-12-01T21:55:09.587Z"),
        completedAt = Instant.parse("2020-12-01T22:56:21.792Z"),
        timedOutDate = Instant.parse("0001-01-01T00:00:00Z"),
        completionState = WorkoutCompletionState.FULL,
        completedAutomatically = false,
        actualDuration = 3514,
        activeEnergyBurned = 570,
        basalEnergyBurned = 83,
        averageHeartRate = 119,
        maxHeartRate = 174,
        difficulty = 0.47820964f,
        notes = "Used an SSB for good mornings, but still a bit light. Tricep extensions were a bit light too.",
        timezone = "America/Chicago",
        isPaused = false,
        isWatchPresent = true,
        setSummaries = mockExerciseSetSummaries,
        updatedDate = Instant.parse("2020-12-01T23:01:42.263707Z")
    )

    val mockWorkout = Workout(
        id = "3a6fe022-e10e-4278-8998-2bb107f230d1",
        name = "Thicker than Good Egg Nog",
        description = "3.1.2 - Lower + Arm Farm",
        type = "workout",
        activityType = "",
        duration = 3288.0,
        scheduledAt = Instant.parse("2020-12-01T08:00:00Z"),
        missedAt = Instant.parse("0001-01-01T00:00:00Z"),
        createdAt = Instant.parse("2020-11-21T19:42:14.618Z"),
        updatedAt = Instant.parse("2020-12-01T22:56:22.297248Z"),
        images = listOf(mockWorkoutImage),
        summaries = listOf(mockWorkoutSummary)
    )
}