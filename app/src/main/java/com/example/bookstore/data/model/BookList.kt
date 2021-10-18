package com.example.bookstore.data.model

import com.google.gson.annotations.SerializedName

data class BookList (

	@SerializedName("results") val results : Results
)