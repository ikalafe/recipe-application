package dehghan.daniyal.recipeapp

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import dehghan.daniyal.recipeapp.model.Category
import dehghan.daniyal.recipeapp.screens.CategoryDetailScreen
import dehghan.daniyal.recipeapp.screens.RecipeScreen
import dehghan.daniyal.recipeapp.screens.Screen
import dehghan.daniyal.recipeapp.viewmodel.MainViewModel

@Composable
fun RecipeApp(modifier: Modifier = Modifier, navHostController: NavHostController) {
    val recipeViewModel: MainViewModel = viewModel()
    val viewState by recipeViewModel.categoriesState


    NavHost(navController = navHostController, startDestination = Screen.RecipeScreen.route) {
        composable(route = Screen.RecipeScreen.route) {
            RecipeScreen(viewState = viewState, navigateToDetail = {
                // The part is responsible for passing data from the current screen to the detail screen
                // It utilize the savedStateHandle, which is a component of the Compose navigation framework
                navHostController.currentBackStackEntry?.savedStateHandle?.set("cat", it)
                navHostController.navigate(Screen.DetailScreen.route)
            })
        }
        composable(route = Screen.DetailScreen.route) {
            val category =
                navHostController.previousBackStackEntry?.savedStateHandle?.get<Category>(key = "cat")
                    ?: Category("", "", "", "")

            CategoryDetailScreen(category = category)
        }
    }
}