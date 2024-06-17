package br.com.fiap.challenge.navegacao

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import br.com.fiap.challenge.CadastroScreen

import br.com.fiap.challenge.calendario.DefaultPreview
import br.com.fiap.challenge.landingpage.LandingPage
import br.com.fiap.challenge.listaEmails.PreviewEmailListScreen
import br.com.fiap.challenge.login.LoginScreen
import br.com.fiap.challenge.map.MapScreen

sealed class Screen(val route: String) {
    object Cadastro : Screen("cadastro")
    object EmailsLista : Screen("caixa_emails")
    object Calendario : Screen("Calendario")
    object LandingPage : Screen("home")
    object LoginScreen : Screen("login")

    object MapScreen : Screen("map")
}

@Composable
fun NavGraph(startDestination: String) {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(Screen.Cadastro.route) {
            CadastroScreen(navController)
        }
        composable(Screen.EmailsLista.route) {
            PreviewEmailListScreen(navController)
        }
        composable(Screen.Calendario.route) {
            DefaultPreview()
        }
        composable(Screen.LandingPage.route) {
            LandingPage(navController)
        }

        composable(Screen.LoginScreen.route) {
            LoginScreen(navController)
        }

        composable(Screen.MapScreen.route) {
            MapScreen(navController)
        }
    }
}