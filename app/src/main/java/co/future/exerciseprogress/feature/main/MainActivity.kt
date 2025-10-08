package co.future.exerciseprogress.feature.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import co.future.exerciseprogress.navigation.WorkoutNavHost
import co.future.exerciseprogress.ui.theme.ExerciseProgressTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ExerciseProgressTheme {
                WorkoutNavHost()
            }
        }
    }
}