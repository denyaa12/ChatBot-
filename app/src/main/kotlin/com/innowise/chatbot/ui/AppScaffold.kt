package com.innowise.chatbot.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.innowise.chatbot.ui.navigation.AppNavGraph

@Composable
fun AppScaffold(
    viewModelFactory: ViewModelProvider.Factory,
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
) {
    Scaffold(
        modifier = modifier.fillMaxSize(),
        containerColor = colorScheme.background,
    ) { innerPadding ->
        AppNavGraph(
            navController = navController,
            viewModelFactory = viewModelFactory,
            modifier = Modifier.padding(innerPadding),
        )
    }
}
