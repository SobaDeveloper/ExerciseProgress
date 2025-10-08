package com.example.domain.usecase

import com.example.data.models.workout.Workout
import com.example.domain.repo.workout.WorkoutRepository
import com.example.domain.usecase.workout.GetWorkouts
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class GetWorkoutsTest {

    private lateinit var getWorkouts: GetWorkouts
    private val repository: WorkoutRepository = mockk()

    @Before
    fun setup() {
        getWorkouts = GetWorkouts(repository)
    }

    @Test
    fun testGetWorkoutsReturnsWorkoutsFromRepository() = runTest {
        val mockWorkouts = listOf(
            Workout(id = "1", name = "Chest & Triceps"),
            Workout(id = "2", name = "Back & Biceps")
        )
        coEvery { repository.getWorkouts() } returns mockWorkouts

        val result = getWorkouts()

        assertEquals(mockWorkouts, result)
        coVerify(exactly = 1) { repository.getWorkouts() }
    }

    @Test
    fun testGetWorkoutsReturnsEmptyList() = runTest {
        coEvery { repository.getWorkouts() } returns emptyList()

        val result = getWorkouts()

        assertEquals(emptyList<Workout>(), result)
        coVerify(exactly = 1) { repository.getWorkouts() }
    }
}
