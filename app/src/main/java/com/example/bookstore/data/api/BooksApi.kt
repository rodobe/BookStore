package com.example.bookstore.data.api

import retrofit2.http.GET

interface BooksApi {

    @GET("books.json")
    suspend fun getBooks()

    @GET("best_sellers.json")
    suspend fun getBestSeller()
}