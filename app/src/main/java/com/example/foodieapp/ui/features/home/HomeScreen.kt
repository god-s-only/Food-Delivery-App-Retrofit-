package com.example.foodieapp.ui.features.home

import android.view.RoundedCorner
import android.widget.Space
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import coil3.compose.AsyncImage
import com.example.foodieapp.data.model.Category

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
        .clickable { onCategorySelected(category) }
        .height(90.dp)) {

        AsyncImage(
            model = category.imageUrl,
            contentDescription = null,
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape),
            contentScale = ContentScale.Inside
        )

        Spacer(modifier = Modifier.size(8.dp))
        Text(
            text = category.name,
            style = MaterialTheme.typography.bodySmall,
            color = Color.Black,
            textAlign = TextAlign.Center
        )
    }
}