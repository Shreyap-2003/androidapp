package com.example.composecustomerapp.ui.home

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import com.example.composecustomerapp.ui.components.Product

data class NoodlesUiState(
    val products: List<Product> = emptyList(),
    val isLoading: Boolean = false
)

class NoodlesViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(NoodlesUiState())
    val uiState: StateFlow<NoodlesUiState> = _uiState.asStateFlow()

    init {
        loadNoodlesData()
    }

    private fun loadNoodlesData() {
        _uiState.value = NoodlesUiState(
            products = listOf(
                Product(
                    id = "n1",
                    name = "Maggie Masala...",
                    price = 56,
                    imageUrl = "https://images.unsplash.com/photo-1569718212165-3a8278d5f624?auto=format&fit=crop&q=80&w=400",
                    tag = "FAST"
                ),
                Product(
                    id = "n2",
                    name = "Yippee Instant...",
                    price = 52,
                    imageUrl = "https://images.unsplash.com/photo-1569718212165-3a8278d5f624?auto=format&fit=crop&q=80&w=400" // Placeholder
                ),
                Product(
                    id = "n3",
                    name = "Korean Ramen",
                    price = 44,
                    imageUrl = "https://images.unsplash.com/photo-1569718212165-3a8278d5f624?auto=format&fit=crop&q=80&w=400", // Placeholder
                    tag = "HOT"
                )
            )
        )
    }
}
