package co.future.exerciseprogress.feature.workout.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import co.future.exerciseprogress.R
import co.future.exerciseprogress.feature.common.GradientOverlay
import co.future.exerciseprogress.feature.common.Tag
import co.future.exerciseprogress.ui.theme.Green500
import co.future.exerciseprogress.ui.theme.Purple500
import co.future.exerciseprogress.ui.theme.Spacing
import coil.compose.AsyncImage
import com.example.core.utils.DateTimeUtil.formatDate
import com.example.core.utils.DateTimeUtil.formatDuration
import com.example.core.utils.extensions.isNotEmpty
import com.example.data.models.workout.Workout
import com.example.data.models.workout.WorkoutImage
import com.example.data.utils.MockData.mockWorkout

@Composable
fun WorkoutImageOverlay(images: List<WorkoutImage>) {
    images.firstOrNull()?.let { image ->
        AsyncImage(
            model = image.url,
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
        GradientOverlay()
    }
}

@Composable
fun WorkoutTags(workout: Workout) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(Spacing.sm),
        modifier = Modifier.padding(bottom = Spacing.sm)
    ) {
        if (workout.isRestDay) {
            Tag(
                text = stringResource(R.string.workout_card_item_label_rest_day),
                color = Purple500
            )
        }
        workout.duration?.takeIf { it > 0 }?.let { duration ->
            Tag(
                text = formatDuration(duration).uppercase(),
                color = Green500
            )
        }
    }
}

@Composable
fun WorkoutTitle(workout: Workout) {
    Text(
        text = workout.name ?: stringResource(R.string.workout_card_item_untitled),
        style = MaterialTheme.typography.titleLarge,
        fontWeight = FontWeight.Bold,
        color = if (workout.images.isNotEmpty()) Color.White else MaterialTheme.colorScheme.onSurface,
        maxLines = 2,
        overflow = TextOverflow.Ellipsis
    )
}

@Composable
fun WorkoutMetadataRow(
    workout: Workout,
    onCardClick: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            workout.scheduledAt?.let { scheduledAt ->
                Text(
                    text = formatDate(scheduledAt),
                    style = MaterialTheme.typography.bodyMedium,
                    color = if (workout.images.isNotEmpty())
                        Color.White.copy(alpha = 0.9f)
                    else
                        MaterialTheme.colorScheme.onSurfaceVariant,
                    fontWeight = FontWeight.Medium
                )
            }

            val exerciseCount = workout.sections.sumOf { it.exerciseSets.size }
            if (exerciseCount > 0) {
                Text(
                    text = "$exerciseCount exercises",
                    style = MaterialTheme.typography.bodySmall,
                    color = if (workout.images.isNotEmpty())
                        Color.White.copy(alpha = 0.7f)
                    else
                        MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }

        if (workout.summaries.isNotEmpty()) {
            Surface(
                onClick = onCardClick,
                shape = RoundedCornerShape(16.dp),
                color = if (workout.images.isNotEmpty())
                    Color.White.copy(alpha = 0.2f)
                else
                    MaterialTheme.colorScheme.primary.copy(alpha = 0.15f)
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_chevron_right_24),
                    contentDescription = "See workout history",
                    tint = if (workout.images.isNotEmpty()) Color.White else MaterialTheme.colorScheme.primary,
                    modifier = Modifier.padding(Spacing.sm)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewWorkoutCardComponents() {
    val workout = mockWorkout
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .background(MaterialTheme.colorScheme.onSurfaceVariant)
    ) {
        WorkoutTags(workout)
        WorkoutTitle(workout)
        WorkoutMetadataRow(workout = workout, onCardClick = {})
    }
}






