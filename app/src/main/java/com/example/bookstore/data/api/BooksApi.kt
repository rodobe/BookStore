package com.example.bookstore.data.api

import com.example.bookstore.data.model.BookList
import retrofit2.http.GET

interface BooksApi {

    @GET("books.json")
    suspend fun getBooks(): Result<BookList>

    @GET("best_sellers.json")
    suspend fun getBestSeller()
}