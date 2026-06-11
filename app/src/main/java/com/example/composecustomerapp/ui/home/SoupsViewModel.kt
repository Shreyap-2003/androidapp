package com.example.composecustomerapp.ui.home

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import com.example.composecustomerapp.ui.components.Product

data class SoupsUiState(
    val products: List<Product> = emptyList(),
    val isLoading: Boolean = false
)

class SoupsViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(SoupsUiState())
    val uiState: StateFlow<SoupsUiState> = _uiState.asStateFlow()

    init {
        loadSoupsData()
    }

    private fun loadSoupsData() {
        _uiState.value = SoupsUiState(
            products = listOf(
                Product(
                    id = "s1",
                    name = "Knorr Hot & Sour Vegetable Soup",
                    price = 52,
                    imageUrl = "https://images.unsplash.com/photo-1547592166-23ac45744acd?auto=format&fit=crop&q=80&w=400"
                ),
                Product(
                    id = "s2",
                    name = "Knorr International...",
                    price = 66,
                    imageUrl = "https://images.unsplash.com/photo-1547592166-23ac45744acd?auto=format&fit=crop&q=80&w=400" // Placeholder
                ),
                Product(
                    id = "s3",
                    name = "Knorr Thick Tomato Soup",
                    price = 52,
                    imageUrl = "https://images.unsplash.com/photo-1547592166-23ac45744acd?auto=format&fit=crop&q=80&w=400" // Placeholder
                )
            )
        )
    }
}
