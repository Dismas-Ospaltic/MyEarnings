package com.gw.myearnings.app_navigation




import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
//google is moving away fro accompanist navigation now we use androidx.navigation.compose
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.gw.myearnings.app_screens.AppOverviewScreen
import com.gw.myearnings.app_screens.AppSettingScreen
import com.gw.myearnings.app_screens.MonthlyEarningScreen
import com.gw.myearnings.app_screens.ThisMonthEarningScreen


/**
 * Sealed class to define all top-level destinations.
 * This gives you type safety instead of raw strings everywhere.
 */
sealed class Screen(val route: String) {
    object ThisMonth : Screen("This Month")
    object AppSettings : Screen("App Settings")

    object AppOverview: Screen("appOverview")

    object MonthlyEarning: Screen("Monthly Earnings")


    object DailyData: Screen("dailyData/{dateId}")

}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AppNavigation(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    /**
     * AnimatedNavHost is the root navigation container for the app.
     * Think of it as the "router" of your entire app.
     */
//    AnimatedNavHost(
//        navController = navController,
//        startDestination = Screen.ThisMonth.route,
//        modifier = modifier,
//
//        // Animation when navigating forward
//        enterTransition = {
//            slideInHorizontally { 1000 } + fadeIn()
//        },
//        exitTransition = {
//            slideOutHorizontally { -1000 } + fadeOut()
//        },
//
//        // Animation when navigating back
//        popEnterTransition = {
//            slideInHorizontally { -1000 } + fadeIn()
//        },
//        popExitTransition = {
//            slideOutHorizontally { 1000 } + fadeOut()
//        }
//
//
//    ) {

    AnimatedNavHost(
        navController = navController,
        startDestination = Screen.ThisMonth.route,

        enterTransition = {
            fadeIn(tween(250)) + slideInHorizontally(
                initialOffsetX = { it / 6 }
            )
        },
        exitTransition = {
            fadeOut(tween(200))
        },

        popEnterTransition = {
            fadeIn(tween(250))
        },
        popExitTransition = {
            fadeOut(tween(200)) + slideOutHorizontally(
                targetOffsetX = { it / 6 }
            )
        }
    ) {


    /**
         * MainApp is the container screen.
         * This is where your Scaffold and (later) bottom navigation will live.
         */
        composable(Screen.ThisMonth.route) {
            ThisMonthEarningScreen(navController)
        }

        /**
         * Example future screen
         */
        composable(Screen.AppSettings.route) {
            AppSettingScreen(navController)
        }

        composable(Screen.AppOverview.route) {
            AppOverviewScreen(navController)
        }


        composable(Screen.MonthlyEarning.route) {
            MonthlyEarningScreen(navController)
        }


//        composable(Screen.DailyData.route) { backStackEntry ->
//            val dateId = backStackEntry.arguments?.getString("dateId") ?: "Unknown"
//            DailyDataScreen(navController, dateId)
//        }
    }
}