package com.example.foodieapp.ui.features.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodieapp.data.FoodAPI
import com.example.foodieapp.data.model.Category
import com.example.foodieapp.data.remote.APIResponse
import com.example.foodieapp.data.remote.safeApiCall
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val foodAPI: FoodAPI): ViewModel() {
    private val _uiState = MutableStateFlow<HomeScreenEvent>(HomeScreenEvent.Nothing)
    val uiState = _uiState.asStateFlow()

    private val _navigationEvent = MutableSharedFlow<HomeScreenNavigationEvent>()
    val navigationEvent = _navigationEvent.asSharedFlow()

    var categories = emptyList<Category>()

    init {
        getCategories()
        getPopularRestaurants()
    }

    fun getCategories(){
        viewModelScope.launch {
            val response = safeApiCall {
                foodAPI.getCategories()
            }
            when(response){
                is APIResponse.Success -> {
                    _uiState.value = HomeScreenEvent.Success
                    categories = response.data.data
                }
                is APIResponse.Error -> {
                    _uiState.value = HomeScreenEvent.Error
                }
            }
        }
    }

    fun getPopularRestaurants(){

    }
}

sealed class HomeScreenEvent{
    data object Success: HomeScreenEvent()
    data object Loading: HomeScreenEvent()
    data object Nothing: HomeScreenEvent()
    data object Error: HomeScreenEvent()
}

sealed class HomeScreenNavigationEvent{
    data object NavigateDetailScreen: HomeScreenNavigationEvent()
}