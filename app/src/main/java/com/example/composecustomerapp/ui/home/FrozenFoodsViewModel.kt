package com.example.composecustomerapp.ui.home

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import com.example.composecustomerapp.ui.components.Product

data class FrozenFoodsUiState(
    val products: List<Product> = emptyList(),
    val isLoading: Boolean = false
)

class FrozenFoodsViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(FrozenFoodsUiState())
    val uiState: StateFlow<FrozenFoodsUiState> = _uiState.asStateFlow()

    init {
        loadData()
    }

    private fun loadData() {
        _uiState.value = FrozenFoodsUiState(
            products = listOf(
                Product(
                    id = "ff1",
                    name = "Kellogg’s Corn Flakes",
                    price = 152,
                    imageUrl = "https://images.unsplash.com/photo-1594489053913-aa82e85b6117?auto=format&fit=crop&q=80&w=400",
                    unit = "1.2 kg"
                ),
                Product(
                    id = "ff2",
                    name = "Saffola Classic- Masala Oats",
                    price = 91,
                    imageUrl = "https://images.unsplash.com/photo-1586444248902-2f64eddc13df?auto=format&fit=crop&q=80&w=400",
                    unit = "400 g"
                ),
                Product(
                    id = "ff3",
                    name = "Kellogg’s Multigrain Chocos",
                    price = 80,
                    imageUrl = "https://images.unsplash.com/photo-1594489053913-aa82e85b6117?auto=format&fit=crop&q=80&w=400", // Placeholder
                    unit = "250 g"
                )
            )
        )
    }
}
