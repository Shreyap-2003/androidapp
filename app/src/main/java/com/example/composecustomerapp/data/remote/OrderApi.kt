package com.example.composecustomerapp.data.remote

import com.example.composecustomerapp.data.model.OrderRequest
import com.example.composecustomerapp.data.model.OrderResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface OrderApi {
    @POST("api/orders")
    suspend fun placeOrder(@Body request: OrderRequest): Response<Unit>

    @GET("api/orders")
    suspend fun getOrders(): Response<List<OrderResponse>>
}
