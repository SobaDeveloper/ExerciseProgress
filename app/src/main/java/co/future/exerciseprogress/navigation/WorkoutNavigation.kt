package co.future.exerciseprogress.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import co.future.exerciseprogress.feature.workout.WorkoutListViewModel
import co.future.exerciseprogress.feature.workout.screens.WorkoutListScreen
import co.future.exerciseprogress.feature.workoutsummary.screens.WorkoutSummaryScreen
import co.future.exerciseprogress.navigation.Screen.WorkoutList
import co.future.exerciseprogress.navigation.Screen.WorkoutSummary

sealed class Screen(val route: String) {
    data object WorkoutList : Screen("workout_list")
    data object WorkoutSummary : Screen("workout_summary/{workoutId}") {
        fun createRoute(workoutId: String) = "workout_summary/$workoutId"
    }
}

@Composable
fun WorkoutNavHost(
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = "workout_graph"
    ) {
        navigation(
            startDestination = WorkoutList.route,
            route = "workout_graph"
        ) {
            composable(WorkoutList.route) { entry ->
                val parentEntry = remember(entry) {
                    navController.getBackStackEntry("workout_graph")
                }
                val viewModel: WorkoutListViewModel = hiltViewModel(parentEntry)

                WorkoutListScreen(
                    viewModel = viewModel,
                    onWorkoutClick = { workoutId ->
                        navController.navigate(WorkoutSummary.createRoute(workoutId))
                    }
                )
            }

            composable(
                route = WorkoutSummary.route,
                arguments = listOf(navArgument("workoutId") { type = NavType.StringType })
            ) { entry ->
                val parentEntry = remember(entry) {
                    navController.getBackStackEntry("workout_graph")
                }
                val viewModel: WorkoutListViewModel = hiltViewModel(parentEntry)

                val workoutId = entry.arguments?.getString("workoutId") ?: ""

                WorkoutSummaryScreen(
                    workoutId = workoutId,
                    viewModel = viewModel,
                    onBackClick = { navController.popBackStack() }
                )
            }
        }
    }
}