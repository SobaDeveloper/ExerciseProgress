package co.future.exerciseprogress.feature.workout.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import co.future.exerciseprogress.ui.theme.CardShape
import co.future.exerciseprogress.ui.theme.Spacing
import com.example.data.models.workout.Workout
import com.example.data.utils.MockData

@Composable
fun WorkoutCard(
    workout: Workout,
    onCardClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp),
        onClick = { if (!workout.isRestDay) onCardClick() },
        shape = CardShape,
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        )
    ) {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {

            WorkoutImageOverlay(images = workout.images)
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomStart)
                    .padding(Spacing.lg)
            ) {
                WorkoutTags(workout)
                WorkoutTitle(workout)
                WorkoutMetadataRow(workout, onCardClick)
            }
        }
    }
}

@Preview
@Composable
fun WorkoutCardPreview() {
    MaterialTheme {
        WorkoutCard(MockData.mockWorkout) {}
    }
}


