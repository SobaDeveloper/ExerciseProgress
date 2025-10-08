package co.future.exerciseprogress.feature.workout

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.models.workout.Workout
import com.example.data.models.workout.WorkoutSummary
import com.example.domain.usecase.workout.GetWorkouts
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class WorkoutListViewModel @Inject constructor(
    private val getWorkouts: GetWorkouts
) : ViewModel() {

    private val _workouts = MutableStateFlow<List<Workout>>(emptyList())
    val workouts: StateFlow<List<Workout>> = _workouts

    private val _workoutSummaries = MutableStateFlow<List<WorkoutSummary>>(emptyList())
    val workoutSummaries: StateFlow<List<WorkoutSummary>> = _workoutSummaries


    init {
        viewModelScope.launch {
            _workouts.value = getWorkouts()
        }
    }

    fun selectWorkout(workoutId: String) {
        val workout = _workouts.value.find { it.id == workoutId }
        _workoutSummaries.value = workout?.summaries ?: emptyList()
    }
}