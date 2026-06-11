package com.example.composecustomerapp.ui.home

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import com.example.composecustomerapp.ui.components.Product

data class CakesUiState(
    val products: List<Product> = emptyList(),
    val isLoading: Boolean = false
)

class CakesViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(CakesUiState())
    val uiState: StateFlow<CakesUiState> = _uiState.asStateFlow()

    init {
        loadCakesData()
    }

    private fun loadCakesData() {
        _uiState.value = CakesUiState(
            products = listOf(
                Product(
                    id = "ca1",
                    name = "Sunfeast Mixed fruit Cake",
                    price = 30,
                    imageUrl = "https://images.unsplash.com/photo-1578985545062-69928b1d9587?auto=format&fit=crop&q=80&w=400"
                ),
                Product(
                    id = "ca2",
                    name = "Britannia Treat Croissant with...",
                    price = 20,
                    imageUrl = "https://images.unsplash.com/photo-1555507036-ab1f4038808a?auto=format&fit=crop&q=80&w=400"
                ),
                Product(
                    id = "ca3",
                    name = "Lotte Choco Pie",
                    price = 45,
                    imageUrl = "https://images.unsplash.com/photo-1582236082449-34b8c9d1c1a5?auto=format&fit=crop&q=80&w=400"
                )
            )
        )
    }
}
