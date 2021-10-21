package com.example.bookstore.data.repository

import com.example.bookstore.networkUtils.Result
import com.example.bookstore.data.local.DataLocalSource
import com.example.bookstore.data.model.BookList
import com.example.bookstore.data.model.Results
import com.example.bookstore.data.remote.DataRemoteSource
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class BooksRepository @Inject constructor(
    private val dataRemoteSource: DataRemoteSource,
    private val dataLocalSource: DataLocalSource
) {

    suspend fun getBooks(): Result<BookList>{
        return dataRemoteSource.getBooks()
    }

    suspend fun getBestSellers(): Result<BookList>{
        return dataRemoteSource.getBestSellers()
    }
}