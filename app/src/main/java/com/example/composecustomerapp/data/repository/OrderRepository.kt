package com.example.composecustomerapp.data.repository

import com.example.composecustomerapp.data.model.OrderRequest
import com.example.composecustomerapp.data.model.OrderResponse
import com.example.composecustomerapp.data.remote.OrderApi
import com.example.composecustomerapp.data.remote.AuthApi

class OrderRepository(
    private val orderApi: OrderApi,
    private val authApi: AuthApi
) {
    suspend fun placeOrder(customerId: Int, itemId: Int): Result<Unit> {
        return try {
            val response = orderApi.placeOrder(OrderRequest(customerId, itemId, 1, "OPEN"))
            if (response.isSuccessful) {
                Result.success(Unit)
            } else {
                Result.failure(Exception("Failed to place order: ${response.code()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun getOrders(): Result<List<OrderResponse>> {
        return try {
            val response = orderApi.getOrders()
            if (response.isSuccessful) {
                Result.success(response.body() ?: emptyList())
            } else {
                Result.failure(Exception("Error fetching orders: ${response.code()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun getPartnerDetails(partnerId: Int) = try {
        val response = authApi.getUser(partnerId)
        if (response.isSuccessful) {
            Result.success(response.body())
        } else {
            Result.failure(Exception("Error fetching partner: ${response.code()}"))
        }
    } catch (e: Exception) {
        Result.failure(e)
    }
}
