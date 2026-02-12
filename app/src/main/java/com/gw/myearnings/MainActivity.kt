package com.gw.myearnings
//
//import android.os.Bundle
//import androidx.activity.ComponentActivity
//import androidx.activity.compose.setContent
//import androidx.activity.enableEdgeToEdge
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.padding
//import androidx.compose.material3.Scaffold
//import androidx.compose.material3.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.tooling.preview.Preview
//import com.gw.myearnings.ui.theme.MyEarningsTheme
//
//class MainActivity : ComponentActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
//        setContent {
//            MyEarningsTheme {
//                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
//                    Greeting(
//                        name = "Android",
//                        modifier = Modifier.padding(innerPadding)
//                    )
//                }
//            }
//        }
//    }
//}
//
//@Composable
//fun Greeting(name: String, modifier: Modifier = Modifier) {
//    Text(
//        text = "Hello $name!",
//        modifier = modifier
//    )
//}
//
//@Preview(showBackground = true)
//@Composable
//fun GreetingPreview() {
//    MyEarningsTheme {
//        Greeting("Android")
//    }
//}




import compose.icons.fontawesomeicons.solid.ChartBar
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.animation.AnimatedVisibility
//import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.expandHorizontally
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.shrinkHorizontally
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import com.gw.myearnings.R
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.view.WindowCompat
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.gw.myearnings.app_navigation.AppNavigation
import com.gw.myearnings.app_navigation.Screen
import com.gw.myearnings.app_screens.ui_components.AddFab
import compose.icons.FontAwesomeIcons
import compose.icons.fontawesomeicons.Solid
import compose.icons.fontawesomeicons.solid.CalendarAlt
import compose.icons.fontawesomeicons.solid.ChartLine
import compose.icons.fontawesomeicons.solid.ClipboardList
import compose.icons.fontawesomeicons.solid.Cog
import compose.icons.fontawesomeicons.solid.Cogs
import compose.icons.fontawesomeicons.solid.FileInvoiceDollar
import compose.icons.fontawesomeicons.solid.Home
import compose.icons.fontawesomeicons.solid.ListAlt
import compose.icons.fontawesomeicons.solid.StoreAlt
import compose.icons.fontawesomeicons.solid.UserCog
import compose.icons.fontawesomeicons.solid.Warehouse
import java.util.Locale

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.S)
    @OptIn(ExperimentalAnimationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Ensure full-screen layout
        WindowCompat.setDecorFitsSystemWindows(window, false)


        // ✅ Enable edge-to-edge layout with transparent system bars
        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.auto(
                lightScrim = Color.TRANSPARENT,
                darkScrim = Color.TRANSPARENT
            ),
            navigationBarStyle = SystemBarStyle.auto(
                lightScrim = Color.TRANSPARENT,
                darkScrim = Color.TRANSPARENT
            )
        )

        setContent {
            val navController = rememberAnimatedNavController()

            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentRoute = navBackStackEntry?.destination?.route

            // Define screens where the bottom bar should be hidden
            val hideBottomBarScreens = listOf(Screen.AppOverview.route,Screen.DailyData.route)

            Scaffold(
                bottomBar = {
//                    BottomNavigationBar(navController)
                    if (currentRoute !in hideBottomBarScreens) {
//                        BottomNavigationBar(navController)
                        // Wrapping in a Box to apply the padding
                        Box(modifier = Modifier.navigationBarsPadding()) {
                            BottomNavigationBar(navController)
                        }
                    }
                },
                floatingActionButton = {
                    if (currentRoute == Screen.ThisMonth.route) { // Show FAB only on Home
                        AddFab(navController)
                    }
                }

            ) { paddingValues ->
                // - status bar
                // - bottom navigation bar
                AppNavigation(
                    navController = navController,
                    modifier = Modifier.padding(
                        bottom = paddingValues.calculateBottomPadding()
                    )
//                    modifier = Modifier.padding(paddingValues)
                )
//                AppNavHost(navController,
//                    Modifier
//                        .padding(paddingValues)
//                        .windowInsetsPadding(WindowInsets.systemBars))
            }
        }
    }





}



@Composable
fun BottomNavigationBar(navController: NavHostController) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination?.route

    val screens = listOf(Screen.ThisMonth, Screen.MonthlyEarning, Screen.AppSettings)

    val backgroundColor = colorResource(id = R.color.bottom_bar_background)
    val selectedColor = colorResource(id = R.color.tab_selected)
    val unselectedColor = colorResource(id = R.color.tab_unselected)
    val indicatorColor = colorResource(id = R.color.tab_indicator).copy(alpha = 0.2f)

    // Using a Surface for the "Floating" effect
    Surface(
        modifier = Modifier
            .padding(start = 24.dp, end = 24.dp, bottom = 24.dp) // Lift it off the bottom
            .fillMaxWidth()
            .height(64.dp),
        shape = RoundedCornerShape(32.dp), // Pill shape
        color = backgroundColor,
        tonalElevation = 8.dp,
        shadowElevation = 10.dp
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            screens.forEach { screen ->
                val isSelected = currentDestination == screen.route

                // Custom Item Layout
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight()
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = null // Removes the default rectangular ripple
                        ) {
                            if (currentDestination != screen.route) {
                                navController.navigate(screen.route) {
                                    popUpTo(Screen.ThisMonth.route) { inclusive = false }
                                    launchSingleTop = true
                                }
                            }
                        },
                    contentAlignment = Alignment.Center
                ) {
                    // Selection Indicator Background
//                    AnimatedVisibility(
//                        visible = isSelected,
//                        enter = fadeIn() + expandHorizontally(),
//                        exit = fadeOut() + shrinkHorizontally()
//                    ) {
                    androidx.compose.animation.AnimatedVisibility(
                        visible = isSelected,
                        enter = fadeIn() + scaleIn(initialScale = 0.8f),
                        exit = fadeOut() + scaleOut(targetScale = 0.8f)
                    ) {
                        Box(
                            modifier = Modifier
                                .size(width = 80.dp, height = 40.dp)
                                .background(indicatorColor, RoundedCornerShape(20.dp))
                        )
                    }

                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Icon(
                            imageVector = when (screen) {
                                is Screen.ThisMonth -> FontAwesomeIcons.Solid.FileInvoiceDollar
                                is Screen.MonthlyEarning -> FontAwesomeIcons.Solid.CalendarAlt
                                is Screen.AppSettings -> FontAwesomeIcons.Solid.Cogs
                                else -> FontAwesomeIcons.Solid.Home
                            },
                            contentDescription = null,
                            tint = if (isSelected) selectedColor else unselectedColor,
                            modifier = Modifier.size(20.dp)
                        )

                        Text(
                            text = screen.route.replaceFirstChar { it.titlecase(Locale.ROOT) },
                            style = MaterialTheme.typography.labelSmall,
                            color = if (isSelected) selectedColor else unselectedColor
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BottomNavigationBarPreview() {
    BottomNavigationBar(navController = rememberNavController())
}

