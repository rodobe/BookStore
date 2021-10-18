package com.example.bookstore.data.repository

import com.example.bookstore.data.local.DataLocalSource
import com.example.bookstore.data.remote.DataRemoteSource
import javax.inject.Inject

class BooksRepository @Inject constructor(
    private val dataRemoteSource: DataRemoteSource,
    private val dataLocalSource: DataLocalSource
) {


}