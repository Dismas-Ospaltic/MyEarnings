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
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import compose.icons.fontawesomeicons.solid.ChartLine
import compose.icons.fontawesomeicons.solid.ClipboardList
import compose.icons.fontawesomeicons.solid.Cog
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
            val hideBottomBarScreens = listOf(Screen.AppOverview.route)

            Scaffold(
                bottomBar = {
//                    BottomNavigationBar(navController)
                    if (currentRoute !in hideBottomBarScreens) {
//                        BottomNavigationBar(navController)
                        BottomNavigationBarModern(navController)
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
    val tabIndicatorColor = colorResource(id = R.color.tab_indicator)

    Column{
        // Top Divider
        HorizontalDivider(
            thickness = 1.dp, // Adjust thickness as needed
            color =  colorResource(id = R.color.tab_indicator)
        )
        NavigationBar(
            containerColor = backgroundColor
        ) {
            screens.forEach { screen ->
                val isSelected = currentDestination == screen.route

                NavigationBarItem(
                    selected = isSelected,
                    onClick = {
                        if (currentDestination != screen.route) {
                            navController.navigate(screen.route) {
                                popUpTo(Screen.ThisMonth.route) { inclusive = false }
                                launchSingleTop = true
                            }
                        }
                    },
                    icon = {

                        when (screen) {
                            is Screen.ThisMonth -> {
                                Icon(
                                    imageVector = FontAwesomeIcons.Solid.FileInvoiceDollar,
                                    contentDescription = "Home",
                                    tint = if (isSelected) selectedColor else unselectedColor,
                                    modifier = Modifier.size(24.dp)
                                )
                            }

                            is Screen.MonthlyEarning -> {
                                Icon(
                                    imageVector = FontAwesomeIcons.Solid.ListAlt,
                                    contentDescription = "history",
                                    tint = if (isSelected) selectedColor else unselectedColor,
                                    modifier = Modifier.size(24.dp)
                                )
                            }

                            // cog icon from font awesome icons
                            is Screen.AppSettings -> {
                                Icon(
                                    imageVector = FontAwesomeIcons.Solid.UserCog,
                                    contentDescription = "setting",
                                    tint = if (isSelected) selectedColor else unselectedColor,
                                    modifier = Modifier.size(24.dp)
                                )
                            }

                            else ->  {
                                Icon(
                                    imageVector = FontAwesomeIcons.Solid.Home,
                                    contentDescription = "home",
                                    tint = if (isSelected) selectedColor else unselectedColor,
                                    modifier = Modifier.size(24.dp)
                                )
                            }
                          // this is for Fallback icon
                        }

                    },
                    label = {
                        Text(
                            text = screen.route.replaceFirstChar { it.titlecase(Locale.ROOT) },
                            color = if (isSelected) selectedColor else unselectedColor // Apply custom colors
                        )
                    },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = selectedColor,
                        unselectedIconColor = unselectedColor,
                        selectedTextColor = selectedColor,
                        unselectedTextColor = unselectedColor,
                        indicatorColor = tabIndicatorColor // this Changes the background color of selected tab
                    )
                )
            }
        }
    }
}


//@Composable
//fun BottomNavigationBarModern(navController: NavHostController) {
//    val navBackStackEntry by navController.currentBackStackEntryAsState()
//    val currentDestination = navBackStackEntry?.destination?.route
//
//    val screens = listOf(Screen.ThisMonth, Screen.MonthlyEarning, Screen.AppSettings)
//
//    val backgroundColor = colorResource(id = R.color.bottom_bar_background)
//    val selectedColor = colorResource(id = R.color.tab_selected)
//    val unselectedColor = colorResource(id = R.color.tab_unselected)
//    val indicatorColor = colorResource(id = R.color.tab_indicator)
//
//    Surface(
//        color = backgroundColor,
//        shadowElevation = 0.dp,
//        modifier = Modifier.fillMaxWidth()
//            .navigationBarsPadding() // <-- adds safe bottom padding
//    ) {
//        Row(
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(vertical = 8.dp),
//            horizontalArrangement = Arrangement.SpaceEvenly,
//            verticalAlignment = Alignment.CenterVertically
//        ) {
//            screens.forEach { screen ->
//                val isSelected = currentDestination == screen.route
//
//                Column(
//                    horizontalAlignment = Alignment.CenterHorizontally,
//                    modifier = Modifier
//                        .clickable {
//                            if (currentDestination != screen.route) {
//                                navController.navigate(screen.route) {
//                                    popUpTo(Screen.MainApp.route) { inclusive = false }
//                                    launchSingleTop = true
//                                }
//                            }
//                        }
//                        .padding(horizontal = 8.dp)
//                ) {
//                    // Icon
//                    Icon(
//                        imageVector = when (screen) {
//                            is Screen.MainApp -> FontAwesomeIcons.Solid.FileInvoiceDollar
//                            is Screen.HistoryData -> FontAwesomeIcons.Solid.ListAlt
//                            is Screen.Settings -> FontAwesomeIcons.Solid.UserCog
//                            else -> FontAwesomeIcons.Solid.Home
//                        },
//                        contentDescription = screen.route,
//                        tint = if (isSelected) selectedColor else unselectedColor,
//                        modifier = Modifier.size(22.dp)
//                    )
//
//                    Spacer(modifier = Modifier.height(2.dp))
//
//                    // Label
//                    Text(
//                        text = screen.route.replaceFirstChar { it.titlecase(Locale.ROOT) },
//                        color = if (isSelected) selectedColor else unselectedColor,
//                        fontSize = 11.sp
//                    )
//
//                    // Small underline indicator for selected tab
//                    if (isSelected) {
//                        Spacer(
//                            modifier = Modifier
//                                .height(2.dp)
//                                .width(24.dp)
//                                .background(indicatorColor, shape = RoundedCornerShape(1.dp))
//                                .padding(top = 2.dp)
//                        )
//                    } else {
//                        Spacer(modifier = Modifier.height(4.dp)) // placeholder
//                    }
//                }
//            }
//        }
//    }
//}

@Preview(showBackground = true)
@Composable
fun BottomNavigationBarPreview() {
    BottomNavigationBar(navController = rememberNavController())
}

//@Preview(showBackground = true)
//@Composable
//fun BottomNavigationBarModernPreview() {
//    BottomNavigationBarModern(navController = rememberNavController())
//}