package com.example.composecustomerapp.ui.home

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import com.example.composecustomerapp.ui.components.Product

data class EnergyDrinksUiState(
    val products: List<Product> = emptyList(),
    val isLoading: Boolean = false
)

class EnergyDrinksViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(EnergyDrinksUiState())
    val uiState: StateFlow<EnergyDrinksUiState> = _uiState.asStateFlow()

    init {
        loadEnergyDrinksData()
    }

    private fun loadEnergyDrinksData() {
        _uiState.value = EnergyDrinksUiState(
            products = listOf(
                Product(
                    id = "ed1",
                    name = "Red Bull Energy...",
                    price = 125,
                    imageUrl = "https://images.unsplash.com/photo-1622543925917-763c34d1538c?auto=format&fit=crop&q=80&w=400"
                ),
                Product(
                    id = "ed2",
                    name = "Monster Energy...",
                    price = 119,
                    imageUrl = "https://images.unsplash.com/photo-1622543925917-763c34d1538c?auto=format&fit=crop&q=80&w=400" // Placeholder
                ),
                Product(
                    id = "ed3",
                    name = "Sting Energy Drink",
                    price = 30,
                    imageUrl = "https://images.unsplash.com/photo-1622543925917-763c34d1538c?auto=format&fit=crop&q=80&w=400" // Placeholder
                )
            )
        )
    }
}
