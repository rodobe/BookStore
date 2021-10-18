package com.example.bookstore.home

import androidx.lifecycle.ViewModel
import com.example.bookstore.data.repository.BooksRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val booksRepository: BooksRepository) :
    ViewModel() {


}