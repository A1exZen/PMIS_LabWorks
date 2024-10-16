package com.example.lab1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.lab1.ui.theme.Lab1Theme
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Lab1Theme {
                Main()
            }
        }
    }
}

@Preview
@Composable
fun Main() {
    val navController = rememberNavController()
    Column(modifier = Modifier.background(Color(0xFFA9D3FA))) {
        NavHost(
            navController,
            startDestination = NavRoutes.Home.route,
            modifier = Modifier.weight(1f)
        ) {
            composable(NavRoutes.Home.route) { Home() }
            composable(NavRoutes.Patients.route) { Patients() }
            composable(NavRoutes.AnimateImg.route) { AnimateImg() }
            composable(NavRoutes.Pictures.route) { Pictures() }
        }
        BottomNavigationBar(navController = navController)
    }
}

@Composable
fun BottomNavigationBar(navController: NavController) {
    NavigationBar(containerColor = Color(0xFF05053D)) {
        val backStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = backStackEntry?.destination?.route

        NavBarItems.BarItems.forEach { navItem ->
            NavigationBarItem(
                selected = currentRoute == navItem.route,
                onClick = {
                    navController.navigate(navItem.route) {
                        popUpTo(navController.graph.findStartDestination().id)
                        { saveState = true }
                        launchSingleTop = true // предотращение записи в стек навигации при переходе к самому себе
                        restoreState = true
                    }
                },
                icon = {
                    Icon(
                        imageVector = navItem.image,
                        contentDescription = navItem.title
                    )
                },
                label = {
                    Text(text = navItem.title)
                }
            )
        }
    }
}

data class BarItem(
    val title: String,
    val image: ImageVector,
    val route: String
)
object NavBarItems {
    val BarItems = listOf(
        BarItem(
            title = "Home",
            image = Icons.Filled.Home,
            route = "home"
        ),
        BarItem(
            title = "Patients",
            image = Icons.Filled.AccountCircle,
            route = "patients"
        ),
        BarItem(
            title = "Animation",
            image = Icons.Filled.Star,
            route = "animate-img"
        ),
        BarItem(
            title = "Pictures",
            image = Icons.Filled.Face,
            route = "pictures"
        ),
    )
}
sealed class NavRoutes(val route: String) {
    object Home : NavRoutes("home")
    object Patients : NavRoutes("patients")
    object AnimateImg : NavRoutes("animate-img")
    object Pictures : NavRoutes("pictures")
}