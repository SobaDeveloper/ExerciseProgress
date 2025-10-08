package com.example.domain.repo

import com.example.data.datasource.workout.WorkoutsLocalSource
import com.example.data.models.workout.Workout
import com.example.domain.repo.workout.WorkoutRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class WorkoutRepositoryTest {

    private lateinit var repository: WorkoutRepository
    private val localSource: WorkoutsLocalSource = mockk()

    @Before
    fun setup() {
        repository = WorkoutRepository(localSource)
    }

    @Test
    fun testGetWorkoutsReturnsDataFromLocalSource() = runTest {
        val mockWorkouts = listOf(
            Workout(id = "1", name = "Leg Day"),
            Workout(id = "2", name = "Push Day")
        )
        coEvery { localSource.getWorkouts() } returns mockWorkouts

        val result = repository.getWorkouts()

        assertEquals(mockWorkouts, result)
        coVerify(exactly = 1) { localSource.getWorkouts() }
    }

    @Test
    fun testGetWorkoutsReturnsEmptyList() = runTest {
        coEvery { localSource.getWorkouts() } returns emptyList()

        val result = repository.getWorkouts()

        assertEquals(emptyList<Workout>(), result)
        coVerify(exactly = 1) { localSource.getWorkouts() }
    }
}