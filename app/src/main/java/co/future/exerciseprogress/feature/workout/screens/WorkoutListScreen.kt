package co.future.exerciseprogress.feature.workout.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import co.future.exerciseprogress.R
import co.future.exerciseprogress.feature.workout.WorkoutListViewModel
import co.future.exerciseprogress.feature.workout.components.WorkoutCard
import co.future.exerciseprogress.ui.theme.Spacing

@Composable
fun WorkoutListScreen(
    viewModel: WorkoutListViewModel,
    onWorkoutClick: (String) -> Unit
) {
    val workouts by viewModel.workouts.collectAsState()

    Scaffold(
        containerColor = MaterialTheme.colorScheme.background
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            contentPadding = PaddingValues(horizontal = Spacing.lg, vertical = Spacing.xl),
            verticalArrangement = Arrangement.spacedBy(Spacing.md)
        ) {
            item {
                Text(
                    text = stringResource(R.string.workout_list_screen_title),
                    style = MaterialTheme.typography.headlineLarge,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = Spacing.sm)
                )
            }

            items(workouts, key = { it.id }) { workout ->
                WorkoutCard(
                    workout = workout,
                    onCardClick = {
                        onWorkoutClick(workout.id)
                    }
                )
            }
        }
    }
}