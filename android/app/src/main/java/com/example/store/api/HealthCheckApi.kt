package com.example.store.api

import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET

interface HealthCheckApi {
    // Укажите эндпоинт вашего бекенда (например, actuator/health или api/health)
    @GET("api/health")
    suspend fun checkHealth(): Response<ResponseBody>
}