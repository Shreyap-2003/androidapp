package com.example.composecustomerapp.ui.home

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import com.example.composecustomerapp.ui.components.SubCategory

data class GroceryUiState(
    val subCategories: List<SubCategory> = emptyList(),
    val exploreMore: List<String> = emptyList(),
    val isLoading: Boolean = false
)

class GroceryViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(GroceryUiState())
    val uiState: StateFlow<GroceryUiState> = _uiState.asStateFlow()

    init {
        loadGroceryData()
    }

    private fun loadGroceryData() {
        _uiState.value = GroceryUiState(
            subCategories = listOf(
                SubCategory(
                    "Dairy Products",
                    "Fresh from local farms delivered daily",
                    "https://images.unsplash.com/photo-1550583724-125581fe2f8a?auto=format&fit=crop&q=80&w=600"
                ),
                SubCategory(
                    "Vegetables",
                    "Organic & pesticide-free selection",
                    "https://images.unsplash.com/photo-1566385101042-1a010ce1d07c?auto=format&fit=crop&q=80&w=600"
                ),
                SubCategory(
                    "Fruits",
                    "Sweet, juicy and seasonally picked",
                    "https://images.unsplash.com/photo-1610832958506-aa56368176cf?auto=format&fit=crop&q=80&w=600"
                )
            ),
            exploreMore = listOf("Organic Produce", "Exotic Fruits", "Leafy Greens", "Root Vegetables")
        )
    }
}
