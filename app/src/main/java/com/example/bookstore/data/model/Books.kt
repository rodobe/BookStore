package com.example.bookstore.data.model

import com.google.gson.annotations.SerializedName

data class Books (

	@SerializedName("isbn") val isbn : Int,
	@SerializedName("title") val title : String,
	@SerializedName("author") val author : String,
	@SerializedName("description") val description : String,
	@SerializedName("genre") val genre : String,
	@SerializedName("img") val img : String,
	@SerializedName("imported") val imported : Boolean
)