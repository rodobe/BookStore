package com.example.bookstore.data.api

import retrofit2.http.GET

interface HomeApi {

    @GET()
    suspend fun getBooks()

    @GET
    suspend fun getBestSeller()
}