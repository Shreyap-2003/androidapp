package com.example.composecustomerapp.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.composecustomerapp.MainApplication
import com.example.composecustomerapp.data.local.TokenManager
import com.example.composecustomerapp.data.repository.AuthRepository
import com.example.composecustomerapp.data.repository.ItemRepository
import com.example.composecustomerapp.data.repository.OrderRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class Category(
    val title: String,
    val description: String,
    val imageUrl: String
)

data class Order(
    val id: String,
    val orderNumber: String,
    val name: String,
    val price: Int,
    val status: String,
    val imageUrl: String,
    val date: String,
    val partnerName: String? = null,
    val partnerPhone: String? = null
)

data class HomeUiState(
    val categories: List<Category> = emptyList(),
    val activeOrders: List<Order> = emptyList(),
    val isAuthenticated: Boolean = false,
    val username: String = "User",
    val userProfileImageUrl: String = "https://images.unsplash.com/photo-1494790108377-be9c29b29330?auto=format&fit=crop&q=80&w=100",
    val cartItems: Map<String, Int> = emptyMap(),
    val isLoading: Boolean = false,
    val allProducts: List<com.example.composecustomerapp.ui.components.Product> = emptyList()
) {
    val cartTotalItems: Int get() = cartItems.values.sum()
    
    val cartProducts: List<Pair<com.example.composecustomerapp.ui.components.Product, Int>>
        get() = cartItems.mapNotNull { (id, qty) ->
            allProducts.find { it.id == id }?.let { it to qty }
        }
    
    val cartSubtotal: Int get() = cartProducts.sumOf { it.first.price * it.second }
}

class HomeViewModel(
    private val orderRepository: OrderRepository,
    private val itemRepository: ItemRepository,
    private val authRepository: AuthRepository,
    private val tokenManager: TokenManager
) : ViewModel() {
    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    init {
        loadHomeData()
        fetchUserDetails()
    }

    private fun loadHomeData() {
        val products = listOf(
            // Dairy
            com.example.composecustomerapp.ui.components.Product("1", "Milk", 27, "https://images.unsplash.com/photo-1550583724-125581fe2f8a?auto=format&fit=crop&q=80&w=400", "FRESH DAILY"),
            com.example.composecustomerapp.ui.components.Product("2", "Cheese", 40, "https://images.unsplash.com/photo-1486297678162-ad2a19b05840?auto=format&fit=crop&q=80&w=400"),
            com.example.composecustomerapp.ui.components.Product("3", "Paneer", 80, "https://images.unsplash.com/photo-1565557623262-b51c2513a641?auto=format&fit=crop&q=80&w=400"),
            // Vegetables
            com.example.composecustomerapp.ui.components.Product("4", "Tomato", 24, "https://images.unsplash.com/photo-1546473422-21f622c07044?auto=format&fit=crop&q=80&w=400", "1 kg", "FRESH"),
            com.example.composecustomerapp.ui.components.Product("5", "Potato", 30, "https://images.unsplash.com/photo-1518977676601-b53f82aba655?auto=format&fit=crop&q=80&w=400", "1 kg")
        )

        _uiState.update { 
            it.copy(
                allProducts = products,
                categories = listOf(
                    Category("Grocery", "Daily Fresh Essentials", "https://images.unsplash.com/photo-1542838132-92c53300491e?auto=format&fit=crop&q=80&w=400"),
                    Category("Cool Drinks & Juices", "Icy Cold Deliveries", "https://images.unsplash.com/photo-1622483767028-3f66f32aef97?auto=format&fit=crop&q=80&w=400"),
                    Category("Bakery", "Fresh from the Oven", "https://images.unsplash.com/photo-1509440159596-0249088772ff?auto=format&fit=crop&q=80&w=400"),
                    Category("Instant Foods", "Quick Meal Solved", "https://images.unsplash.com/photo-1569718212165-3a8278d5f624?auto=format&fit=crop&q=80&w=400")
                )
            )
        }
    }

    private fun fetchUserDetails() {
        viewModelScope.launch {
            val userIdStr = tokenManager.userId.first()
            val userId = userIdStr?.toIntOrNull()
            
            if (userId != null) {
                _uiState.update { it.copy(isAuthenticated = true) }
                val result = authRepository.getUser(userId)
                result.onSuccess { user ->
                    val name = user.name ?: "User"
                    val firstName = name.split(" ").firstOrNull() ?: name
                    _uiState.update { it.copy(username = firstName) }
                }
            } else {
                _uiState.update { it.copy(isAuthenticated = false) }
            }
        }
    }

    fun setAuthenticated(isAuthenticated: Boolean) {
        _uiState.update { it.copy(isAuthenticated = isAuthenticated) }
        if (isAuthenticated) {
            fetchUserDetails()
        }
    }

    fun updateCart(productId: String, delta: Int) {
        _uiState.update { state ->
            val currentQty = state.cartItems[productId] ?: 0
            val newQty = (currentQty + delta).coerceAtLeast(0)
            val newCartItems = state.cartItems.toMutableMap()
            if (newQty > 0) {
                newCartItems[productId] = newQty
            } else {
                newCartItems.remove(productId)
            }
            state.copy(cartItems = newCartItems)
        }
    }

    fun removeItemFromCart(productId: String) {
        _uiState.update { state ->
            val newCartItems = state.cartItems.toMutableMap()
            newCartItems.remove(productId)
            state.copy(cartItems = newCartItems)
        }
    }

    fun placeOrder(productId: String, onSuccess: () -> Unit, onError: (String) -> Unit) {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            val userIdStr = tokenManager.userId.first()
            val userId = userIdStr?.toIntOrNull()
            val itemId = productId.toIntOrNull()
            
            if (userId != null && itemId != null) {
                val result = orderRepository.placeOrder(userId, itemId)
                result.onSuccess {
                    _uiState.update { it.copy(isLoading = false) }
                    removeItemFromCart(productId)
                    onSuccess()
                }.onFailure { e ->
                    _uiState.update { it.copy(isLoading = false) }
                    onError(e.message ?: "Failed to place order")
                }
            } else {
                _uiState.update { it.copy(isLoading = false) }
                onError("User not logged in or invalid product")
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as MainApplication)
                HomeViewModel(
                    application.orderRepository,
                    application.itemRepository,
                    application.authRepository,
                    application.tokenManager
                )
            }
        }
    }
}
