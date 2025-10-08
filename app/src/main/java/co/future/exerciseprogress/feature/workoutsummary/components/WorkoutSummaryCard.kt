package co.future.exerciseprogress.feature.workoutsummary.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import co.future.exerciseprogress.R
import co.future.exerciseprogress.ui.theme.Blue500
import co.future.exerciseprogress.ui.theme.CardShape
import co.future.exerciseprogress.ui.theme.Orange500
import co.future.exerciseprogress.ui.theme.Red500
import co.future.exerciseprogress.ui.theme.SectionShape
import co.future.exerciseprogress.ui.theme.Spacing
import com.example.core.utils.DateTimeUtil
import com.example.core.utils.DateTimeUtil.formatDate
import com.example.core.utils.DateTimeUtil.formatTime
import com.example.data.models.workout.WorkoutSummary
import com.example.data.utils.MockData

@Composable
fun WorkoutSummaryCard(summary: WorkoutSummary) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = CardShape,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(Spacing.lg)
        ) {
            WorkoutSummaryHeader(summary)
            Spacer(modifier = Modifier.height(Spacing.lg))
            WorkoutSummaryStats(summary)
            WorkoutSummaryInfo(summary)
            WorkoutSummaryNotes(summary)
        }
    }
}

@Composable
private fun WorkoutSummaryHeader(summary: WorkoutSummary) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            summary.startedAt?.let {
                Text(
                    text = formatDate(it),
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.SemiBold
                )
            }
            summary.completedAt?.let {
                Text(
                    text = stringResource(R.string.workout_summary_header_finished, formatTime(it)),
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
        CompletionBadge(completionState = summary.completionState)
    }
}

@Composable
private fun WorkoutSummaryStats(summary: WorkoutSummary) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(Spacing.md)
    ) {
        summary.actualDuration?.let { duration ->
            StatCard(
                drawableInt = R.drawable.ic_timer_24,
                value = DateTimeUtil.formatDuration(duration.toDouble()),
                label = stringResource(R.string.workout_summary_stat_duration),
                color = Blue500,
                modifier = Modifier.weight(1f)
            )
        }

        summary.activeEnergyBurned?.let { calories ->
            StatCard(
                drawableInt = R.drawable.ic_flame_24,
                value = calories.toString(),
                label = stringResource(R.string.workout_summary_stat_calories),
                color = Orange500,
                modifier = Modifier.weight(1f)
            )
        }

        summary.averageHeartRate?.let { avgHr ->
            StatCard(
                drawableInt = R.drawable.ic_ecg_heart_24,
                value = "$avgHr",
                label = stringResource(R.string.workout_summary_state_average_heartrate),
                color = Red500,
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@Composable
private fun WorkoutSummaryInfo(summary: WorkoutSummary) {
    if (summary.maxHeartRate != null || summary.difficulty != null || summary.notes?.isNotBlank() == true) {
        Spacer(modifier = Modifier.height(Spacing.lg))
        Divider(color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.2f))
        Spacer(modifier = Modifier.height(Spacing.lg))
    }

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(Spacing.lg)
    ) {
        summary.maxHeartRate?.let { maxHr ->
            InfoItem(label = "Max HR", value = "$maxHr bpm")
        }

        summary.difficulty?.let { diff ->
            InfoItem(label = "Difficulty", value = String.format("%.1f/5", diff))
        }
    }
}

@Composable
private fun WorkoutSummaryNotes(summary: WorkoutSummary) {
    summary.notes?.takeIf { it.isNotBlank() }?.let { notes ->
        Spacer(modifier = Modifier.height(Spacing.md))
        Surface(
            color = MaterialTheme.colorScheme.surface,
            shape = SectionShape
        ) {
            Text(
                text = notes,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.padding(Spacing.md)
            )
        }
    }
}

@Preview
@Composable
fun PreviewWorkoutSummaryCard() {
    MaterialTheme {
        WorkoutSummaryCard(summary = MockData.mockWorkoutSummary)
    }
}