package com.example.composecustomerapp.ui.home

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import com.example.composecustomerapp.ui.components.SubCategory

data class CoolDrinksUiState(
    val subCategories: List<SubCategory> = emptyList(),
    val isLoading: Boolean = false
)

class CoolDrinksViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(CoolDrinksUiState())
    val uiState: StateFlow<CoolDrinksUiState> = _uiState.asStateFlow()

    init {
        loadCoolDrinksData()
    }

    private fun loadCoolDrinksData() {
        _uiState.value = CoolDrinksUiState(
            subCategories = listOf(
                SubCategory(
                    "Soft Drinks",
                    "Colas, Soda & Mixers",
                    "https://images.unsplash.com/photo-1622483767028-3f66f32aef97?auto=format&fit=crop&q=80&w=600"
                ),
                SubCategory(
                    "Fruit Juices",
                    "Freshly pressed & pulpy",
                    "https://images.unsplash.com/photo-1613478223719-2ab802602423?auto=format&fit=crop&q=80&w=600"
                ),
                SubCategory(
                    "Energy Drinks",
                    "Caffeinated & Electrolytes",
                    "https://images.unsplash.com/photo-1622543925917-763c34d1538c?auto=format&fit=crop&q=80&w=600"
                )
            )
        )
    }
}
