package com.example.bookstore.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bookstore.networkUtils.Event
import com.example.bookstore.networkUtils.Result
import com.example.bookstore.data.repository.BooksRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val booksRepository: BooksRepository) :
    ViewModel() {

    private val _bookState =
        MutableStateFlow<Event<BookState>>(Event(BookState.OnLoading(isVisible = true)))
    val bookState: StateFlow<Event<BookState>>
        get() = _bookState

    fun getBooks() {
        viewModelScope.launch {
            booksRepository.getBooks()
                .flowOn(Dispatchers.IO)
                .onStart { _bookState.value = Event(BookState.OnLoading(isVisible = true)) }
                .map {
                    when (it) {
                        is Result.Error<*> -> {
                            Event(BookState.OnError)
                            Event(BookState.OnLoading(isVisible = false))
                        }
                        is Result.Success -> {
                            Event(BookState.SuccessBooks)
                            Event(BookState.OnLoading(isVisible = false))
                        }
                    }
                }
                .catch { Log.e("BOOKS", "Error: $it.message.orEmpty()") }
                .collect { _bookState.value = it }
        }
    }

    fun getBestSellers() {
        viewModelScope.launch {

        }
    }
}

sealed class BookState {
    data class OnLoading(val isVisible: Boolean) : BookState()
    object SuccessBooks : BookState()
    object SuccessBestSellers: BookState()
    object OnError : BookState()
}