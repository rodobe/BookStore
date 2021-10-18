package com.example.bookstore.data.remote

import com.example.bookstore.data.api.BooksApi
import com.example.bookstore.networkUtils.NetworkResponseHandler.apiCall
import com.example.bookstore.networkUtils.Result
import com.example.bookstore.data.model.BookList
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class DataRemoteSource @Inject constructor(private val booksApi: BooksApi) {

    suspend fun getBooks(): Flow<Result<BookList>> {
        return flow {  emit(apiCall{ booksApi.getBooks()})}
    }

    suspend fun getBestSellers() {
        booksApi.getBestSeller()
    }
}