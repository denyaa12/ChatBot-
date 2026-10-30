package com.innowise.chatbot.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.innowise.chatbot.ui.screen.chat.ChatRoute
import com.innowise.chatbot.ui.screen.test.TestRoute

@Composable
fun AppNavGraph(
    navController: NavHostController,
    viewModelFactory: ViewModelProvider.Factory,
    modifier: Modifier = Modifier,
) {
    NavHost(
        navController = navController,
        startDestination = AppDestination.Test,
        modifier = modifier,
    ) {
        composable<AppDestination.Test> {
            TestRoute(
                viewModelFactory = viewModelFactory,
                onNavigateToChats = { navController.navigate(AppDestination.Chats) },
            )
        }

        composable<AppDestination.Chats> {
            ChatRoute(
                viewModelFactory = viewModelFactory,
                onNavigateBack = { navController.popBackStack() },
            )
        }
    }
}
