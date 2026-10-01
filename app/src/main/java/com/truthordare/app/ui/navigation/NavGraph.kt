package com.truthordare.app.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.truthordare.app.data.Category
import com.truthordare.app.ui.screens.AgeVerifyScreen
import com.truthordare.app.ui.screens.CategoryScreen
import com.truthordare.app.ui.screens.GameScreen
import com.truthordare.app.ui.screens.HomeScreen
import com.truthordare.app.ui.screens.SessionSummaryScreen
import com.truthordare.app.ui.screens.SettingsScreen
import com.truthordare.app.ui.screens.SetupScreen
import com.truthordare.app.viewmodel.SettingsViewModel

sealed class Route(val path: String) {
    data object AgeVerify : Route("age_verify")
    data object Home : Route("home")
    data object Category : Route("category")
    data object Setup : Route("setup/{category}")
    data object Game : Route("game/{category}")
    data object Summary : Route("summary")
    data object Settings : Route("settings")
}

@Composable
fun TruthOrDareNavGraph(settingsViewModel: SettingsViewModel) {
    val navController = rememberNavController()
    val settings by settingsViewModel.settings.collectAsState()

    NavHost(navController = navController, startDestination = Route.AgeVerify.path) {
        composable(Route.AgeVerify.path) {
            AgeVerifyScreen(
                onVerified = { navController.navigate(Route.Home.path) { popUpTo(Route.AgeVerify.path) { inclusive = true } } }
            )
        }

        composable(Route.Home.path) {
            HomeScreen(
                settings = settings,
                onCategoryClick = { navController.navigate(Route.Category.path) },
                onSettingsClick = { navController.navigate(Route.Settings.path) }
            )
        }

        composable(Route.Category.path) {
            CategoryScreen(
                onCategorySelected = { category ->
                    navController.navigate("setup/${category.name}")
                }
            )
        }

        composable(
            route = Route.Setup.path,
            arguments = listOf(navArgument("category") { type = NavType.StringType })
        ) { backStackEntry ->
            val categoryName = backStackEntry.arguments?.getString("category") ?: "BEDROOM"
            val category = Category.valueOf(categoryName)
            SetupScreen(
                category = category,
                onStart = { navController.navigate("game/${category.name}") },
                onBack = { navController.popBackStack() }
            )
        }

        composable(
            route = Route.Game.path,
            arguments = listOf(navArgument("category") { type = NavType.StringType })
        ) { backStackEntry ->
            val categoryName = backStackEntry.arguments?.getString("category") ?: "BEDROOM"
            val category = Category.valueOf(categoryName)
            GameScreen(
                category = category,
                settings = settings,
                onSummary = { navController.navigate(Route.Summary.path) },
                onBack = { navController.popBackStack() }
            )
        }

        composable(Route.Summary.path) {
            SessionSummaryScreen(
                onHome = { navController.navigate(Route.Home.path) { popUpTo(Route.Home.path) { inclusive = true } } }
            )
        }

        composable(Route.Settings.path) {
            SettingsScreen(
                settings = settings,
                onSettingChanged = { settingsViewModel.updateSetting { it } },
                onBack = { navController.popBackStack() }
            )
        }
    }
}
