package com.example.bookstore.data.repository

import com.example.bookstore.data.api.Result
import com.example.bookstore.data.local.DataLocalSource
import com.example.bookstore.data.model.BookList
import com.example.bookstore.data.remote.DataRemoteSource
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class BooksRepository @Inject constructor(
    private val dataRemoteSource: DataRemoteSource,
    private val dataLocalSource: DataLocalSource
) {

    suspend fun getBooks(): Flow<Result<BookList>>{
        return flow {emit(dataRemoteSource.getBooks())}
    }

    suspend fun getBestSellers(){
        dataRemoteSource.getBestSellers()
    }
}