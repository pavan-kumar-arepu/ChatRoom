package com.ppam.chatroom.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.ppam.chatroom.AuthViewModel
import com.ppam.chatroom.LoginScreen
import com.ppam.chatroom.SignUpScreen

@Composable
fun NavigationGraph(
    authViewModel: AuthViewModel,
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = Screen.SignUpScreen.route
    ) {
        composable(Screen.SignUpScreen.route) {
            SignUpScreen (
                authViewModel = authViewModel,
                onNavigateToLogin = {
                    navController.navigate(Screen.LoginScreen.route) }
                )
            }
        composable(Screen.LoginScreen.route) {
            LoginScreen (
                authViewModel = authViewModel,
                onNavigateToSignUp = {
                    navController.navigate(Screen.SignUpScreen.route) }
            ) {
                navController.navigate(Screen.ChatRoomScreen.route)
            }
        }
        }
    }
