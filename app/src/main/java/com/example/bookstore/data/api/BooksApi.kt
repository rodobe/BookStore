package com.example.bookstore.data.api

import com.example.bookstore.data.model.BookList
import com.example.bookstore.data.model.Results
import retrofit2.Response
import retrofit2.http.GET

interface BooksApi {

    @GET("books.json")
    suspend fun getBooks(): Response<BookList>

    @GET("best_sellers.json")
    suspend fun getBestSeller(): Response<BookList>
}