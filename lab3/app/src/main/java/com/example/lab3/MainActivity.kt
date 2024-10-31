package com.example.lab3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

enum class Screens(val title:String) {
    Start("Lunch tray"),
    EntreeMenu("Choose Entree"),
    SideDishMenu("Choose Side Dish"),
    AccompanimentMenu("Choose Accompaniment"),
    Checkout("Order checkout")
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val orderViewModel: OrderViewModel = viewModel()
            Lab3 (orderViewModel)
            }
        }
}

@Composable
fun Lab3(orderViewModel: OrderViewModel) {
    val navController = rememberNavController()
    Scaffold  {
        innerPadding ->
        NavHost(navController, startDestination = Screens.Start.name, Modifier.padding(innerPadding)) {
            createScreens(navController, orderViewModel)
        }
    }
}

fun NavGraphBuilder.createScreens(navController: NavController, orderViewModel: OrderViewModel) {
    composable(Screens.Start.name) {
        StartScreen(
            navController = navController,
            nextScreen = Screens.EntreeMenu
        )
    }
    composable(Screens.EntreeMenu.name) {
        EntreeMenuScreen(
            navController = navController,
            nextScreen = Screens.SideDishMenu,
            orderViewModel = orderViewModel
        )
    }
    composable(Screens.SideDishMenu.name) {
        SideDishMenuScreen(
            navController = navController,
            nextScreen = Screens.AccompanimentMenu,
            orderViewModel = orderViewModel
        )
    }
    composable(Screens.AccompanimentMenu.name) {
        AccompanimentMenuScreen(
            navController = navController,
            nextScreen = Screens.Checkout,
            orderViewModel = orderViewModel
        )
    }
    composable(Screens.Checkout.name) {
        CheckoutScreen(
            navController = navController,
            orderViewModel = orderViewModel)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StartScreen(
    navController: NavController,
    nextScreen: Screens
) {
    Scaffold(
        topBar = {
            TopAppBar(
                modifier = Modifier.fillMaxWidth(),
                title = {
                    Box(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        val currentRoute = navController.currentBackStackEntry?.destination?.route
                        val screenTitle = Screens.entries.find { it.name == currentRoute }?.title
                        Text(
                            text = screenTitle ?: Screens.Start.title,
                            modifier = Modifier.align(Alignment.Center)
                        )
                    }
                },
                actions = {
                    val canNavigateBack = navController.previousBackStackEntry != null
                    if (canNavigateBack) {
                        Spacer(modifier = Modifier.width(48.dp))
                    } else
                        Spacer(modifier = Modifier.width(16.dp))
                }
            )
        }
    ) { contentPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(contentPadding)
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            Button(
                onClick = { navController.navigate(nextScreen.name) },
                modifier = Modifier.fillMaxWidth(0.7f)
            ) {
                Text("Start order")
            }
        }
    }
}

@Composable
fun MenuItemCard(
    item: MenuItem,
    selected: Boolean,
    onSelect: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .border(
                BorderStroke(if (selected ) 3.dp else 1.dp, if (selected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface),
                shape = MaterialTheme.shapes.medium
            )
            .clickable { onSelect() }
            .padding(16.dp)
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(text = item.name, style = MaterialTheme.typography.titleMedium)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = item.description, style = MaterialTheme.typography.bodyMedium)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = "$${item.price}", style = MaterialTheme.typography.bodySmall)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EntreeMenuScreen(
        navController: NavController,
        nextScreen: Screens,
        orderViewModel: OrderViewModel
    ) {
    var selectedEntreeId = remember { mutableStateOf<MenuItem?>(null) }
    Scaffold(
        topBar = {
            TopAppBar(
                modifier = Modifier.fillMaxWidth(),
                title = {
                    Box(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        val currentRoute = navController.currentBackStackEntry?.destination?.route
                        val screenTitle = Screens.entries.find { it.name == currentRoute }?.title
                        Text(
                            text = screenTitle ?: Screens.Start.title,
                            modifier = Modifier.align(Alignment.Center)
                        )
                    }
                },
                navigationIcon = {
                    val canNavigateBack = navController.previousBackStackEntry != null
                    if (canNavigateBack) {
                        IconButton(onClick = { navController.navigateUp() }) {
                            Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                        }
                    }
                },
                actions = {
                    val canNavigateBack = navController.previousBackStackEntry != null
                    if (canNavigateBack) {
                        Spacer(modifier = Modifier.width(48.dp))
                    } else
                        Spacer(modifier = Modifier.width(16.dp))
                }
            )
        }
    ) { contentPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(contentPadding)
                .padding(16.dp)
        ) {

            LazyColumn(
                modifier = Modifier
                    .weight(1f)
            ) {
                items(entreeMenuItems) { item ->
                    MenuItemCard(
                        item = item,
                        selected = item == selectedEntreeId.value,
                        onSelect = { selectedEntreeId.value = item }
                    )
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
            ) {
                Button(
                    onClick = {
                        navController.navigate(Screens.Start.name) {
                            popUpTo(0)
                            launchSingleTop = true
                        }
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = contentColorFor(MaterialTheme.colorScheme.primary),
                        contentColor = MaterialTheme.colorScheme.primary
                    ),
                    border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary),
                    modifier = Modifier
                        .weight(1f)
                ) {
                    Text("Cancel")
                }
                Spacer(modifier = Modifier.width(8.dp))
                Button(
                    onClick = {
                        navController.navigate(nextScreen.name)
                        orderViewModel.selectedEntree.value = selectedEntreeId.value
                    },
                    enabled = selectedEntreeId.value != null,
                    modifier = Modifier
                        .weight(1f)
                ) {
                    Text("Next")
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SideDishMenuScreen(
    navController: NavController,
    nextScreen: Screens,
    orderViewModel: OrderViewModel
) {
    var selectedSideDishId = remember { mutableStateOf<MenuItem?>(null) }
    Scaffold(
        topBar = {
            TopAppBar(
                modifier = Modifier.fillMaxWidth(),
                title = {
                    Box(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        val currentRoute = navController.currentBackStackEntry?.destination?.route
                        val screenTitle = Screens.entries.find { it.name == currentRoute }?.title
                        Text(
                            text = screenTitle ?: Screens.Start.title,
                            modifier = Modifier.align(Alignment.Center)
                        )
                    }
                },
                navigationIcon = {
                    val canNavigateBack = navController.previousBackStackEntry != null
                    if (canNavigateBack) {
                        IconButton(onClick = { navController.navigateUp() }) {
                            Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                        }
                    }
                },
                actions = {
                    val canNavigateBack = navController.previousBackStackEntry != null
                    if (canNavigateBack) {
                        Spacer(modifier = Modifier.width(48.dp))
                    } else
                        Spacer(modifier = Modifier.width(16.dp))
                }
            )
        }
    ) { contentPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(contentPadding)
                .padding(16.dp)
        ) {

            LazyColumn(
                modifier = Modifier
                    .weight(1f)
            ) {
                items(sideDishMenuItems) { item ->
                    MenuItemCard(
                        item = item,
                        selected = item == selectedSideDishId.value,
                        onSelect = { selectedSideDishId.value = item }
                    )
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
            ) {
                Button(
                    onClick = {
                        navController.navigate(Screens.Start.name) {
                            popUpTo(0)
                            launchSingleTop = true
                        }
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = contentColorFor(MaterialTheme.colorScheme.primary),
                        contentColor = MaterialTheme.colorScheme.primary
                    ),
                    border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary),
                    modifier = Modifier
                        .weight(1f)
                ) {
                    Text("Cancel")
                }
                Spacer(modifier = Modifier.width(8.dp))
                Button(
                    onClick = {
                        navController.navigate(nextScreen.name)
                        orderViewModel.selectedSideDish.value = selectedSideDishId.value
                    },
                    enabled = selectedSideDishId.value != null,
                    modifier = Modifier
                        .weight(1f)
                ) {
                    Text("Next")
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AccompanimentMenuScreen(
    navController: NavController,
    nextScreen: Screens,
    orderViewModel: OrderViewModel
) {
    var selectedAccompanimentId = remember { mutableStateOf<MenuItem?>(null) }
    Scaffold(
        topBar = {
            TopAppBar(
                modifier = Modifier.fillMaxWidth(),
                title = {
                    Box(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        val currentRoute = navController.currentBackStackEntry?.destination?.route
                        val screenTitle = Screens.entries.find { it.name == currentRoute }?.title
                        Text(
                            text = screenTitle ?: Screens.Start.title,
                            modifier = Modifier.align(Alignment.Center)
                        )
                    }
                },
                navigationIcon = {
                    val canNavigateBack = navController.previousBackStackEntry != null
                    if (canNavigateBack) {
                        IconButton(onClick = { navController.navigateUp() }) {
                            Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                        }
                    }
                },
                actions = {
                    val canNavigateBack = navController.previousBackStackEntry != null
                    if (canNavigateBack) {
                        Spacer(modifier = Modifier.width(48.dp))
                    } else
                        Spacer(modifier = Modifier.width(16.dp))
                }
            )
        }
    ) { contentPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(contentPadding)
                .padding(16.dp)
        ) {

            LazyColumn(
                modifier = Modifier
                    .weight(1f)
            ) {
                items(accompanimentMenuItems) { item ->
                    MenuItemCard(
                        item = item,
                        selected = item == selectedAccompanimentId.value,
                        onSelect = { selectedAccompanimentId.value = item }
                    )
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
            ) {
                Button(
                    onClick = {
                        navController.navigate(Screens.Start.name) {
                            popUpTo(0)
                            launchSingleTop = true
                        }
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = contentColorFor(MaterialTheme.colorScheme.primary),
                        contentColor = MaterialTheme.colorScheme.primary
                    ),
                    border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary),
                    modifier = Modifier
                        .weight(1f)
                ) {
                    Text("Cancel")
                }
                Spacer(modifier = Modifier.width(8.dp))
                Button(
                    onClick = {
                        navController.navigate(nextScreen.name)
                        orderViewModel.selectedAccompaniment.value = selectedAccompanimentId.value
                    },
                    enabled = selectedAccompanimentId.value != null,
                    modifier = Modifier
                        .weight(1f)
                ) {
                    Text("Next")
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CheckoutScreen(
    navController: NavController,
    orderViewModel: OrderViewModel
) {
    Scaffold(
        topBar = {
            TopAppBar(
                modifier = Modifier.fillMaxWidth(),
                title = {
                    Box(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        val currentRoute = navController.currentBackStackEntry?.destination?.route
                        val screenTitle = Screens.entries.find { it.name == currentRoute }?.title
                        Text(
                            text = screenTitle ?: Screens.Start.title,
                            modifier = Modifier.align(Alignment.Center)
                        )
                    }
                },
                navigationIcon = {
                    val canNavigateBack = navController.previousBackStackEntry != null
                    if (canNavigateBack) {
                        IconButton(onClick = { navController.navigateUp() }) {
                            Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                        }
                    }
                },
                actions = {
                    val canNavigateBack = navController.previousBackStackEntry != null
                    if (canNavigateBack) {
                        Spacer(modifier = Modifier.width(48.dp))
                    } else
                        Spacer(modifier = Modifier.width(16.dp))
                }
            )
        }
    ) { contentPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(contentPadding)
                .padding(16.dp)
        ) {
            Text(
                "Order Summary",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            @Composable
            fun displaySelectedItem(item: MenuItem?) {
                Row(modifier = Modifier.fillMaxWidth().padding(4.dp)) {
                    Text(text = item?.name ?: "None", modifier = Modifier.weight(1f))
                    Text(
                        text = "$${item?.price ?: 0.0}",
                        modifier = Modifier.align(Alignment.CenterVertically)
                    )
                }
            }

            displaySelectedItem(orderViewModel.selectedEntree.value)
            displaySelectedItem(orderViewModel.selectedSideDish.value)
            displaySelectedItem(orderViewModel.selectedAccompaniment.value)

            Spacer(modifier = Modifier.height(8.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(1.dp)
                    .background(color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.2f))
            )

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(4.dp),
                horizontalArrangement = Arrangement.End
            ) {
                Text(
                    "Total: $${orderViewModel.calculateTotalPrice()}",
                    style = MaterialTheme.typography.titleMedium
                )
            }

            Spacer(modifier = Modifier.weight(1f))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
            ) {
                Button(
                    onClick = {
                        navController.navigate(Screens.Start.name) {
                            popUpTo(0)
                            launchSingleTop = true
                        }
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = contentColorFor(MaterialTheme.colorScheme.primary),
                        contentColor = MaterialTheme.colorScheme.primary
                    ),
                    border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary),
                    modifier = Modifier
                        .weight(1f)
                ) {
                    Text("Cancel")
                }
                Spacer(modifier = Modifier.width(8.dp))
                Button(
                    onClick = {
                        navController.navigate(Screens.Start.name) {
                            popUpTo(0)
                            launchSingleTop = true
                        }
                    },
                    modifier = Modifier
                        .weight(1f)
                ) {
                    Text("Submit")
                }
            }
        }
    }
}
