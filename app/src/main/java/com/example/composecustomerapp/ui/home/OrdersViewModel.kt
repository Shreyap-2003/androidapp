package com.example.composecustomerapp.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.composecustomerapp.MainApplication
import com.example.composecustomerapp.data.local.TokenManager
import com.example.composecustomerapp.data.model.UserResponse
import com.example.composecustomerapp.data.repository.ItemRepository
import com.example.composecustomerapp.data.repository.OrderRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class OrdersUiState(
    val activeOrders: List<Order> = emptyList(),
    val completedOrders: List<Order> = emptyList(),
    val selectedTab: OrderTab = OrderTab.ACTIVE,
    val isLoading: Boolean = false
)

enum class OrderTab {
    ACTIVE, COMPLETED
}

class OrdersViewModel(
    private val orderRepository: OrderRepository,
    private val itemRepository: ItemRepository,
    private val tokenManager: TokenManager
) : ViewModel() {
    private val _uiState = MutableStateFlow(OrdersUiState())
    val uiState: StateFlow<OrdersUiState> = _uiState.asStateFlow()

    init {
        loadOrders()
    }

    fun loadOrders() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            val userIdStr = tokenManager.userId.first()
            val userId = userIdStr?.toIntOrNull()
            
            if (userId == null) {
                _uiState.update { it.copy(isLoading = false) }
                return@launch
            }

            // Fetch items first to map names/images
            val itemsResult = itemRepository.getItems()
            val allItems = itemsResult.getOrDefault(emptyList())

            val ordersResult = orderRepository.getOrders()
            ordersResult.onSuccess { orderResponses ->
                val orders = mutableListOf<Order>()
                
                for (resp in orderResponses) {
                    if (resp.customerId != userId) continue
                    
                    val item = allItems.find { it.id == resp.itemId }
                    
                    var partnerName: String? = null
                    var partnerPhone: String? = null
                    
                    // Requirement: if IN_PROGRESS, show as ASSIGNED and fetch partner details
                    if (resp.orderStatus == "IN_PROGRESS" && resp.partnerId != null) {
                        val partnerResult = orderRepository.getPartnerDetails(resp.partnerId)
                        partnerResult.onSuccess { partner: UserResponse? ->
                            partnerName = partner?.name
                            partnerPhone = partner?.phoneNumber
                        }
                    }

                    orders.add(
                        Order(
                            id = resp.id.toString(),
                            orderNumber = resp.id.toString(),
                            name = item?.name ?: "Item #${resp.itemId}",
                            price = item?.price?.toInt() ?: 0,
                            status = if (resp.orderStatus == "IN_PROGRESS") "ASSIGNED" else resp.orderStatus ?: "OPEN",
                            imageUrl = item?.imageUrl ?: "",
                            date = resp.createdTime?.take(10) ?: "",
                            partnerName = partnerName,
                            partnerPhone = partnerPhone
                        )
                    )
                }
                
                _uiState.update { 
                    it.copy(
                        activeOrders = orders.filter { o -> o.status != "COMPLETED" },
                        completedOrders = orders.filter { o -> o.status == "COMPLETED" },
                        isLoading = false
                    )
                }
            }.onFailure {
                _uiState.update { it.copy(isLoading = false) }
            }
        }
    }

    fun setSelectedTab(tab: OrderTab) {
        _uiState.update { it.copy(selectedTab = tab) }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as MainApplication)
                OrdersViewModel(
                    application.orderRepository,
                    application.itemRepository,
                    application.tokenManager
                )
            }
        }
    }
}
