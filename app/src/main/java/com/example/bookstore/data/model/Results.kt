package com.example.bookstore.data.model

import com.google.gson.annotations.SerializedName


data class Results (

	@SerializedName("books") val books : List<Books>,
	@SerializedName("best_sellers") val best_sellers : List<Int>
)