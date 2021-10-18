package com.example.bookstore.networkUtils

data class NetworkError(val errorCode: String? = null, val errorMessage: String? = null) :
    Exception(errorMessage)