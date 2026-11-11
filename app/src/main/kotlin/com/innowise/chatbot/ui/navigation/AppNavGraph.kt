package com.innowise.chatbot.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.innowise.chatbot.ui.screen.auth.AuthRoute
import com.innowise.chatbot.ui.screen.chat.ChatRoute
import com.innowise.chatbot.ui.screen.onboarding.OnboardingRoute
import com.innowise.chatbot.ui.screen.signin.SignInRoute
import com.innowise.chatbot.ui.screen.splash.SplashRoute
import com.innowise.chatbot.ui.screen.test.TestRoute

@Composable
fun AppNavGraph(
    navController: NavHostController,
    viewModelFactory: ViewModelProvider.Factory,
    modifier: Modifier = Modifier,
) {
    NavHost(
        navController = navController,
        startDestination = AppDestination.Splash,
        modifier = modifier,
    ) {
        composable<AppDestination.Splash> {
            SplashRoute(
                viewModelFactory = viewModelFactory,
                onNavigateToOnboarding = {
                    navController.navigate(AppDestination.Onboarding) {
                        popUpTo<AppDestination.Splash> { inclusive = true }
                    }
                },
            )
        }

        composable<AppDestination.Onboarding> {
            OnboardingRoute(
                viewModelFactory = viewModelFactory,
                onNavigateToAuth = {
                    navController.navigate(AppDestination.Auth) {
                        popUpTo<AppDestination.Onboarding> { inclusive = true }
                    }
                },
            )
        }

        composable<AppDestination.Auth> {
            AuthRoute(
                viewModelFactory = viewModelFactory,
                onNavigateToSignIn = { navController.navigate(AppDestination.SignIn) },
                onNavigateToChats = {
                    navController.navigate(AppDestination.Chats) {
                        popUpTo<AppDestination.Auth> { inclusive = true }
                    }
                },
            )
        }

        composable<AppDestination.SignIn> {
            SignInRoute(
                viewModelFactory = viewModelFactory,
                onNavigateBack = { navController.popBackStack() },
                onNavigateToChats = {
                    navController.navigate(AppDestination.Chats) {
                        popUpTo<AppDestination.Auth> { inclusive = true }
                    }
                },
            )
        }

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
