package com.example.lab1

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Button
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lab1.ui.theme.Lab1Theme
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.layout
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.Navigator
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Main()
        }
    }

}

@Composable
fun Main() {
    val navController = rememberNavController()
    Column(Modifier.padding(8.dp)) {
        NavHost(
            navController,
            startDestination = NavRoutes.Home.route,
            modifier = Modifier.weight(1f)
        ) {
            composable(NavRoutes.Home.route) { Home() }
            composable(NavRoutes.Lists.route) { Lists() }
        }
        BottomNavigationBar(navController = navController)
    }
}

@Composable
fun BottomNavigationBar(navController: NavController) {
    NavigationBar {
        val backStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = backStackEntry?.destination?.route

        NavBarItems.BarItems.forEach { navItem ->
            NavigationBarItem(
                selected = currentRoute == navItem.route,
                onClick = {
                    navController.navigate(navItem.route) {
                        popUpTo(navController.graph.findStartDestination().id)
                        { saveState = true }
                        launchSingleTop =
                            true // предотращение записи в стек навигации при переходе к самому себе
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

object NavBarItems {
    val BarItems = listOf(
        BarItem(
            title = "Home",
            image = Icons.Filled.Home,
            route = "home"
        ),
        BarItem(
            title = "Lists",
            image = Icons.Filled.Menu,
            route = "lists"
        ),
    )
}

data class BarItem(
    val title: String,
    val image: ImageVector,
    val route: String
)

@Composable
fun Home() {
    var text by rememberSaveable { mutableStateOf("") }
    val initialFullName = stringResource(id = R.string.full_name)
    var fullName by rememberSaveable { mutableStateOf(initialFullName) }
//    Text("Home Page", fontSize = 30.sp)
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFA9D3FA)),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        item {
            Text(
                text = "$fullName",
                modifier = Modifier.padding(16.dp),
                fontSize = 35.sp,
                textAlign = TextAlign.Center,
                lineHeight = 40.sp
            )
        }
        item {
            Image(
                painter = painterResource(id = R.drawable.android_studio_logo),
                modifier = Modifier.height(200.dp),
                contentDescription = "Android Image"
            )
        }
        item {
            TextField(
                value = text,
                onValueChange = { text = it },
                label = { Text(text = "Введите имя:") },
                placeholder = { Text(text = "Иванов И. И.") },
                modifier = Modifier
                    .padding(2.dp, 50.dp, 2.dp, 20.dp)
                    .fillMaxWidth()
            )
        }
        item {
            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(0.dp, 0.dp, 0.dp, 20.dp)
            ) {
                Button(onClick = { fullName = text }, shape = CutCornerShape(10)) {
                    Text("Ввод")
                }
                FilledTonalButton(onClick = { text = "" }, shape = CutCornerShape(10)) {
                    Text("Очистить")
                }
            }
        }
    }
}

@Composable
fun Lists() {
    Text("Lists Page", fontSize = 30.sp)
}

sealed class NavRoutes(val route: String) {
    object Home : NavRoutes("home")

    //    object Contacts : NavRoutes("contacts")
    object Lists : NavRoutes("lists")
}