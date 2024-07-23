package com.syan.bvi

import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("path/to/your/endpoint")// reemplazar por endpoint
    fun getUsuarios(): Call<List<Usuario>>
}
