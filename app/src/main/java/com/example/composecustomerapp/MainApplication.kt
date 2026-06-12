package com.example.composecustomerapp

import android.app.Application
import com.example.composecustomerapp.data.local.TokenManager
import com.example.composecustomerapp.data.remote.RetrofitClient
import com.example.composecustomerapp.data.repository.AuthRepository
import com.example.composecustomerapp.data.repository.ItemRepository
import com.example.composecustomerapp.data.repository.OrderRepository

class MainApplication : Application() {
    lateinit var tokenManager: TokenManager
    lateinit var authRepository: AuthRepository
    lateinit var itemRepository: ItemRepository
    lateinit var orderRepository: OrderRepository

    override fun onCreate() {
        super.onCreate()
        tokenManager = TokenManager(this)
        val authApi = RetrofitClient.createAuthApi(tokenManager)
        val itemApi = RetrofitClient.createItemApi(tokenManager)
        val orderApi = RetrofitClient.createOrderApi(tokenManager)
        
        authRepository = AuthRepository(authApi, tokenManager)
        itemRepository = ItemRepository(itemApi)
        orderRepository = OrderRepository(orderApi, authApi)
    }
}
