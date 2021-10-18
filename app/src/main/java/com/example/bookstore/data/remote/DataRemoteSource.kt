package com.example.bookstore.data.remote

import com.example.bookstore.data.api.BooksApi
import com.example.bookstore.data.api.Result
import com.example.bookstore.data.model.BookList
import javax.inject.Inject

class DataRemoteSource @Inject constructor(private val booksApi: BooksApi) {

    suspend fun getBooks(): Result<BookList> {
        return booksApi.getBooks()
    }

    suspend fun getBestSellers() {
        booksApi.getBestSeller()
    }
}