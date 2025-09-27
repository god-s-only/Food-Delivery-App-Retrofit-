package com.example.foodieapp.ui.features.home

import android.view.RoundedCorner
import android.widget.Space
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import coil3.compose.AsyncImage
import com.example.foodieapp.data.model.Category
import com.example.foodieapp.data.model.Restaurant
import com.example.foodieapp.ui.theme.Orange
import com.example.foodieapp.ui.theme.Typography
import com.example.foodieapp.R

@Composable
fun HomeScreen(navController: NavController,viewModel: HomeViewModel = hiltViewModel()) {
    val uiState = viewModel.uiState.collectAsStateWithLifecycle()
    Column(modifier = Modifier
        .fillMaxSize()) {
        when(uiState.value){
            is HomeScreenEvent.Success -> {
                val categories = viewModel.categories
                CategoryList(categories = categories) {
                    navController.navigate("category/${it.id}")
                }
            }

            HomeScreenEvent.Error -> TODO()
            HomeScreenEvent.Loading -> TODO()
            HomeScreenEvent.Nothing -> TODO()
        }
    }
}


@Composable
fun CategoryList(categories: List<Category>, onCategorySelected: (Category) -> Unit) {
    LazyRow {
        items(categories){ category ->
            CategoryItem(category, onCategorySelected = onCategorySelected)
        }
    }
}


@Composable
fun CategoryItem(category: Category, onCategorySelected: (Category) -> Unit) {
    Column(modifier = Modifier
        .padding(8.dp)
        .clip(RoundedCornerShape(45.dp))
        .width(60.dp)
        .clickable { onCategorySelected(category) }
        .shadow(
            3.dp,
            RoundedCornerShape(45.dp),
            ambientColor = Color.Gray.copy(alpha = 0.8f),
            spotColor = Color.Gray.copy(alpha = 0.8f)
        )
        .background(color = Color.Gray.copy(alpha = 0.1f))
        .height(90.dp),
        horizontalAlignment = Alignment.CenterHorizontally) {

        AsyncImage(
            model = category.imageUrl,
            contentDescription = null,
            modifier = Modifier
                .size(40.dp)
                .shadow(
                    3.dp,
                    RoundedCornerShape(45.dp),
                    ambientColor = Orange,
                    spotColor = Orange
                )
                .clip(CircleShape),
            contentScale = ContentScale.Inside
        )

        Spacer(modifier = Modifier.size(8.dp))
        Text(
            text = category.name,
            style = MaterialTheme.typography.bodySmall,
            color = Color.Black,
            textAlign = TextAlign.Center,
            fontSize = 10.sp
        )
    }
}

@Composable
fun RestaurantList(restaurant: List<Restaurant>, onRestaurantSelected: (Restaurant) -> Unit) {
    LazyRow {
        items(restaurant){

        }
    }
}

@Composable
fun RestaurantItem(restaurant: Restaurant, onRestaurantSelected: (Restaurant) -> Unit) {
    Box(modifier = Modifier
        .width(142.dp)
        .height(76.dp)
        .clip(RoundedCornerShape(16.dp))
    ){
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            AsyncImage(
                model = restaurant.imageUrl,
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Inside
            )
            Column(modifier = Modifier
                .background(color = Color.Black.copy(alpha = 0.5f))
                .clip(RoundedCornerShape(16.dp))
                .weight(2.5f)
                .background(Color.White)
                .clickable{ onRestaurantSelected(restaurant)}) {
                Text(
                    text = restaurant.name,
                    style = Typography.titleMedium,
                    textAlign = TextAlign.Center
                )
                Row() {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Image(
                            painter = painterResource(id = R.id.delivery),
                            contentDescription = null,
                            modifier = Modifier
                                .padding(8.dp)
                                .size(12.dp)
                        )
                        Text(
                            text = "Free delivery",
                            style = Typography.bodySmall
                        )
                    }
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Image(
                            painter = painterResource(id = R.id.time),
                            contentDescription = null,
                            modifier = Modifier
                                .padding(8.dp)
                                .size(12.dp)
                        )
                        Text(
                            text = "Free delivery",
                            style = Typography.bodySmall
                        )
                    }
                }
            }
        }

    }
}