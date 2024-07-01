package dehghan.daniyal.recipeapp.screens

sealed class Screen(val route: String) {
    object RecipeScreen : Screen(route = "recipescreen")
    object DetailScreen : Screen(route = "detailscreen")
}