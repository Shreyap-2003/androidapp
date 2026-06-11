package com.example.composecustomerapp.ui.home

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import com.example.composecustomerapp.ui.components.Product

data class FruitJuicesUiState(
    val products: List<Product> = emptyList(),
    val isLoading: Boolean = false
)

class FruitJuicesViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(FruitJuicesUiState())
    val uiState: StateFlow<FruitJuicesUiState> = _uiState.asStateFlow()

    init {
        loadJuicesData()
    }

    private fun loadJuicesData() {
        _uiState.value = FruitJuicesUiState(
            products = listOf(
                Product(
                    id = "fj1",
                    name = "Maaza Mango fruit Juice",
                    price = 34,
                    imageUrl = "https://images.unsplash.com/photo-1547514701-42782101795e?auto=format&fit=crop&q=80&w=400"
                ),
                Product(
                    id = "fj2",
                    name = "Paper Boat Mixed berries Juice",
                    price = 40,
                    imageUrl = "https://images.unsplash.com/photo-1613478223719-2ab802602423?auto=format&fit=crop&q=80&w=400"
                ),
                Product(
                    id = "fj3",
                    name = "B Natural Guava Juice",
                    price = 48,
                    imageUrl = "https://images.unsplash.com/photo-1547514701-42782101795e?auto=format&fit=crop&q=80&w=400"
                )
            )
        )
    }
}
