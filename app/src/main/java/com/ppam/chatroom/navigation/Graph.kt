package com.ppam.chatroom.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.ppam.chatroom.LoginScreen
import com.ppam.chatroom.SignUpScreen

@Composable
fun NavigationGraph(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = Screen.SignUpScreen.route
    ) {
        composable(Screen.SignUpScreen.route) {
            SignUpScreen (
                onNavigateToLogin = {
                    navController.navigate(Screen.LoginScreen.route) }
                )
            }
        composable(Screen.LoginScreen.route) {
            LoginScreen (
                onNavigateToSignUp = {
                    navController.navigate(Screen.SignUpScreen.route) }
            )
        }
        }
    }
