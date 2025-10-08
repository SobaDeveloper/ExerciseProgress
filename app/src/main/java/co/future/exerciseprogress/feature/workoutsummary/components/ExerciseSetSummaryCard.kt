package co.future.exerciseprogress.feature.workoutsummary.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import co.future.exerciseprogress.R
import co.future.exerciseprogress.ui.theme.CardShape
import co.future.exerciseprogress.ui.theme.SectionShape
import co.future.exerciseprogress.ui.theme.Spacing
import com.example.data.models.workout.ExerciseSetCompletionState
import com.example.data.models.workout.ExerciseSetSummary
import com.example.data.utils.MockData

@Composable
fun ExerciseSetSummaryList(
    setSummaries: List<ExerciseSetSummary>,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = CardShape,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(Spacing.md),
            verticalArrangement = Arrangement.spacedBy(Spacing.mdSm)
        ) {
            Text(
                text = stringResource(R.string.exercise_set_summary_title),
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )

            setSummaries.forEach { setSummary ->
                ExerciseSetSummaryCard(setSummary = setSummary)
            }
        }
    }
}

@Composable
fun ExerciseSetSummaryCard(
    setSummary: ExerciseSetSummary,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = SectionShape,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(Spacing.md),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = setSummary.exerciseSet?.exercise?.name ?: stringResource(R.string.exercise_set_summary_name_default),
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.SemiBold
                )

                Spacer(modifier = Modifier.height(Spacing.xs))
                setSummary.timeSpentActive?.let { seconds ->
                    Text(
                        text = "${seconds}s",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
            ExerciseSetSummaryStats(setSummary = setSummary)
        }
    }
}

@Composable
private fun ExerciseSetSummaryStats(setSummary: ExerciseSetSummary) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(Spacing.lg),
        verticalAlignment = Alignment.CenterVertically
    ) {
        setSummary.weight?.let { weight ->
            StatItem(
                value = "${weight.toInt()}",
                label = stringResource(R.string.exercise_set_summary_weight)
            )
        }

        val reps = setSummary.repsReported ?: setSummary.repsCounted
        reps?.let {
            StatItem(
                value = "$it",
                label = stringResource(R.string.exercise_set_summary_reps)
            )
        } ?: setSummary.exerciseSet?.reps?.let { targetReps ->
            StatItem(
                value = "$targetReps",
                label = stringResource(R.string.exercise_set_summary_reps)
            )
        }

        CompletionIndicator(setSummary.completionState)
    }
}

@Composable
private fun StatItem(
    value: String,
    label: String
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = value,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = label,
            style = MaterialTheme.typography.labelSmall,
            fontSize = 10.sp,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

@Composable
private fun CompletionIndicator(state: ExerciseSetCompletionState) {
    val color = when (state) {
        ExerciseSetCompletionState.FULL -> Color(0xFF4CAF50)
        ExerciseSetCompletionState.PARTIAL -> Color(0xFFFFC107)
        ExerciseSetCompletionState.NONE -> Color(0xFF9E9E9E)
    }

    Box(
        modifier = Modifier
            .size(12.dp)
            .background(color, RoundedCornerShape(6.dp))
    )
}

@Preview
@Composable
fun PreviewExerciseSetSummaryList() {
    MaterialTheme {
        ExerciseSetSummaryList(
            setSummaries = MockData.mockWorkoutSummary.setSummaries
        )
    }
}