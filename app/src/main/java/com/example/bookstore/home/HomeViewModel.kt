package com.example.bookstore.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bookstore.data.model.Books
import com.example.bookstore.data.model.Sections
import com.example.bookstore.networkUtils.Event
import com.example.bookstore.networkUtils.Result
import com.example.bookstore.data.repository.BooksRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val booksRepository: BooksRepository) :
    ViewModel() {

    private val _bookState = MutableLiveData<Event<BookState>>()
    val bookState: LiveData<Event<BookState>>
        get() = _bookState
    private var bestSellers: List<Long> = listOf()

    fun getBooks() {
        viewModelScope.launch {
            _bookState.value = Event(BookState.OnLoading(isVisible = true))
            when (val result = booksRepository.getBooks()) {
                is Result.Error<*> -> {
                    _bookState.value = Event(BookState.OnError)
                    _bookState.value = Event(BookState.OnLoading(isVisible = false))
                }
                is Result.Success -> {
                    getBestSellersBooks(result.data.results.books)
                    getBooksByGenre(result.data.results.books)
                }
            }
        }
    }

    private fun getBooksByGenre(books: List<Books>) {
        val historyBooks: MutableList<Books> = mutableListOf()
        val scienceBooks: MutableList<Books> = mutableListOf()
        val businessBooks: MutableList<Books> = mutableListOf()
        books.forEach {
            if (it.genre == Sections.HISTORY.section) {
                historyBooks.add(it)
            }
            if (it.genre == Sections.SCIENCE.section) {
                scienceBooks.add(it)
            }
            if (it.genre == Sections.BUSINESS.section) {
                businessBooks.add(it)
            }
        }
        _bookState.value = Event(BookState.SuccessHistoryBooks(historyBooks = historyBooks))
        _bookState.value = Event(BookState.SuccessScienceBooks(scienceBooks = scienceBooks))
        _bookState.value = Event(BookState.SuccessBusinessBooks(businessBooks = businessBooks))
        _bookState.value = Event(BookState.OnLoading(isVisible = false))
    }

    fun getBestSellers() {
        viewModelScope.launch {
            when (val result = booksRepository.getBestSellers()) {
                is Result.Success -> {
                    getBooks()
                    bestSellers = result.data.results.best_sellers
                }
                is Result.Error<*> -> {
                    _bookState.value = Event(BookState.OnError)
                    _bookState.value = Event(BookState.OnLoading(isVisible = false))
                }
            }
        }
    }

    private fun getBestSellersBooks(books: List<Books>){
        val bestSellersBooks = books.filter { book -> bestSellers.find { it == book.isbn } != null }
        _bookState.value = Event(BookState.SuccessBestSellers(bestSellers = bestSellersBooks))
        _bookState.value = Event(BookState.OnLoading(isVisible = false))
    }
}

sealed class BookState {
    data class OnLoading(val isVisible: Boolean) : BookState()
    data class SuccessHistoryBooks(val historyBooks: List<Books>) : BookState()
    data class SuccessScienceBooks(val scienceBooks: List<Books>) : BookState()
    data class SuccessBusinessBooks(val businessBooks: List<Books>) : BookState()
    data class SuccessBestSellers(val bestSellers: List<Books>) : BookState()
    object OnError : BookState()
}