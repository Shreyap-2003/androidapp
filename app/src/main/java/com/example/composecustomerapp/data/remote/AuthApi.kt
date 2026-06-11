package com.example.composecustomerapp.data.remote

import com.example.composecustomerapp.data.model.LoginRequest
import com.example.composecustomerapp.data.model.LoginResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {
    @POST("application/auth/login")
    suspend fun login(@Body request: LoginRequest): Response<LoginResponse>
}
