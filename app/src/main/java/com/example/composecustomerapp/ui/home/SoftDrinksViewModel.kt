package com.example.composecustomerapp.ui.home

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import com.example.composecustomerapp.ui.components.Product

data class SoftDrinksUiState(
    val products: List<Product> = emptyList(),
    val isLoading: Boolean = false
)

class SoftDrinksViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(SoftDrinksUiState())
    val uiState: StateFlow<SoftDrinksUiState> = _uiState.asStateFlow()

    init {
        loadSoftDrinksData()
    }

    private fun loadSoftDrinksData() {
        _uiState.value = SoftDrinksUiState(
            products = listOf(
                Product(
                    id = "sd1",
                    name = "Coca-Cola",
                    price = 39,
                    imageUrl = "https://images.unsplash.com/photo-1622483767028-3f66f32aef97?auto=format&fit=crop&q=80&w=400",
                    unit = "250 ml"
                ),
                Product(
                    id = "sd2",
                    name = "Sprite",
                    price = 35,
                    imageUrl = "https://images.unsplash.com/photo-1624517452488-04869289c4ca?auto=format&fit=crop&q=80&w=400",
                    unit = "250 ml"
                ),
                Product(
                    id = "sd3",
                    name = "Fanta",
                    price = 38,
                    imageUrl = "https://images.unsplash.com/photo-1624517452488-04869289c4ca?auto=format&fit=crop&q=80&w=400", // Using a placeholder for Fanta
                    unit = "250 ml"
                )
            )
        )
    }
}
