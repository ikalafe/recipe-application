package dehghan.daniyal.recipeapp.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import dehghan.daniyal.recipeapp.model.Category
import dehghan.daniyal.recipeapp.ui.theme.Gray
import dehghan.daniyal.recipeapp.ui.theme.OrangeColor
import dehghan.daniyal.recipeapp.viewmodel.MainViewModel

@Composable
fun RecipeScreen(
    navigateToDetail: (Category) -> Unit,
    viewState: MainViewModel.RecipeState
) {
    // Ui Application
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 30.dp, bottom = 30.dp)
    ) {
        when {

            // If the data was being loaded
            viewState.loading -> {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }

            // If there is an error in loading the data
            viewState.error != null -> {
                Text(text = "ERROR OCCURRED")
            }
            // If data is found and loaded
            else -> {
                // Display Categories
                CategoryScreen(categories = viewState.list, navigateToDetail)
            }
        }
    }
}

@Composable
fun CategoryScreen(categories: List<Category>, navigateToDetail: (Category) -> Unit) {
    LazyVerticalGrid(columns = GridCells.Fixed(2), modifier = Modifier.fillMaxSize()) {
        items(categories) { category ->
            CategoryItem(category = category, navigateToDetail)
        }
    }
}

// How Each Item Look Like
@Composable
fun CategoryItem(category: Category, navigateToDetail: (Category) -> Unit) {
    Column(modifier = Modifier.padding(16.dp)) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(16.dp))
                .shadow(1.dp)
                .background(Gray)
                .padding(8.dp),
            contentAlignment = Alignment.Center,
        ) {
            Column(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxSize()
                    .clip(RoundedCornerShape(16.dp)),
                /*.clickable {}*/
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Box(
                    modifier = Modifier
                        .padding(16.dp),
                ) {
                    Image(
                        painter = rememberAsyncImagePainter(category.strCategoryThumb),
                        contentDescription = null,
                        modifier = Modifier
                            .aspectRatio(1f)
                            .clip(RoundedCornerShape(50))
                            .wrapContentSize(),
                    )
                }
                Text(
                    text = category.strCategory,
                    color = Color.Black,
                    style = TextStyle(fontWeight = FontWeight.Bold),
                    modifier = Modifier.padding(top = 4.dp)
                )
                Spacer(modifier = Modifier.height(16.dp))
                Button(
                    onClick = {
                        navigateToDetail(category)
                    },
                    colors = ButtonColors(
                        containerColor = OrangeColor,
                        contentColor = Color.Black,
                        disabledContentColor = Color.Black,
                        disabledContainerColor = OrangeColor
                    ),
                    modifier = Modifier.width(180.dp)
                ) {
                    Text("See Recipe", fontSize = 12.sp, color = Color.White)
                }
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}