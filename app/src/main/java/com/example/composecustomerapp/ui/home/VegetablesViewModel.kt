package com.example.composecustomerapp.ui.home

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import com.example.composecustomerapp.ui.components.Product

data class VegetablesUiState(
    val products: List<Product> = emptyList(),
    val isLoading: Boolean = false
)

class VegetablesViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(VegetablesUiState())
    val uiState: StateFlow<VegetablesUiState> = _uiState.asStateFlow()

    init {
        loadVegetablesData()
    }

    private fun loadVegetablesData() {
        _uiState.value = VegetablesUiState(
            products = listOf(
                Product(
                    "v1",
                    "Potato",
                    25,
                    "https://images.unsplash.com/photo-1518977676601-b53f02bad675?auto=format&fit=crop&q=80&w=400"
                ),
                Product(
                    "v2",
                    "Tomato",
                    21,
                    "https://images.unsplash.com/photo-1518977822534-7049a61ee0c2?auto=format&fit=crop&q=80&w=400"
                ),
                Product(
                    "v3",
                    "Carrot",
                    28,
                    "https://images.unsplash.com/photo-1444312645910-ffa973656eba?auto=format&fit=crop&q=80&w=400"
                )
            )
        )
    }
}
