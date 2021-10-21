package com.example.bookstore.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.ConcatAdapter
import com.example.bookstore.data.model.Sections
import com.example.bookstore.databinding.FragmentHomeBinding
import com.example.bookstore.home.adapters.BestSellersAdapter
import com.example.bookstore.home.adapters.BooksAdapter
import com.example.bookstore.home.adapters.HorizontalSectionAdapter
import com.example.bookstore.networkUtils.EventObserver
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private val viewModel: HomeViewModel by viewModels()
    private lateinit var binding: FragmentHomeBinding

    private val concatAdapter = ConcatAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setObserver()
        viewModel.getBestSellers()
    }

    private fun setObserver() {
        viewModel.bookState.observe(viewLifecycleOwner, EventObserver { state ->
            when (state) {
                is BookState.SuccessHistoryBooks -> {
                    concatAdapter.addAdapter(
                        HorizontalSectionAdapter(
                            Sections.HISTORY.section, BooksAdapter(
                                state.historyBooks
                            )
                        )
                    )
                }
                is BookState.SuccessScienceBooks -> {
                    concatAdapter.addAdapter(
                        HorizontalSectionAdapter(
                            Sections.SCIENCE.section, BooksAdapter(
                                state.scienceBooks
                            )
                        )
                    )
                }
                is BookState.SuccessBusinessBooks -> {
                    concatAdapter.addAdapter(
                        HorizontalSectionAdapter(
                            Sections.BUSINESS.section, BooksAdapter(
                                state.businessBooks
                            )
                        )
                    )
                }
                is BookState.SuccessBestSellers -> {
                    concatAdapter.addAdapter(
                        HorizontalSectionAdapter(
                            Sections.BEST_SELLERS.section, BestSellersAdapter(
                                state.bestSellers
                            )
                        )
                    )
                }
                is BookState.OnLoading -> {
                    if (state.isVisible) binding.progressIndicator.show()
                    else binding.progressIndicator.hide()
                }
                is BookState.OnError -> {

                }
            }
        })
        binding.recyclerHome.adapter = concatAdapter
    }

    companion object {

        fun newInstance() = HomeFragment()
    }
}