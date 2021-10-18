package com.example.bookstore.data.remote

import com.example.bookstore.data.api.BooksApi
import javax.inject.Inject

class DataRemoteSource @Inject constructor(private val booksApi: BooksApi) {

    suspend fun getBooks() {
        booksApi.getBooks()
    }

    suspend fun getBestSellers() {
        booksApi.getBestSeller()
    }
}