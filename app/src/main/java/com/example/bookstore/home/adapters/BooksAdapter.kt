package com.example.bookstore.home.adapters

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.palette.graphics.Palette
import androidx.recyclerview.widget.RecyclerView
import com.example.bookstore.R
import com.example.bookstore.data.model.Books
import com.example.bookstore.databinding.AdapterBookBinding
import com.example.bookstore.extensions.getContrastColor
import com.squareup.picasso.Picasso
import com.squareup.picasso.Target

class BooksAdapter(private val books: List<Books>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.adapter_book, parent, false)
        return BooksViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as BooksViewHolder).bind(books[position])
    }

    override fun getItemCount(): Int {
        return books.size
    }

    class BooksViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = AdapterBookBinding.bind(itemView)

        fun bind(book: Books) {
            setBookImg(book.img)
            binding.apply {
                authorName.text = book.author
                bookTitle.text = book.title
            }
        }

        private fun setBookImg(img: String) {
            Picasso.get().load(img).placeholder(R.drawable.ic_image_not_supported).error(R.drawable.ic_image_not_supported).into(object :
                Target {

                override fun onBitmapLoaded(bitmap: Bitmap?, from: Picasso.LoadedFrom?) {
                    binding.bookImg.setImageBitmap(bitmap)
                    getDominantColor(bitmap)
                }

                override fun onBitmapFailed(e: Exception?, errorDrawable: Drawable?) {
                }

                override fun onPrepareLoad(placeHolderDrawable: Drawable?) {
                }

            })
        }

        private fun getDominantColor(bitmap: Bitmap?) {
            if (bitmap != null) {
                Palette.Builder(bitmap).generate {
                    binding.apply {
                        containerBooks.setBackgroundColor(
                            it?.dominantSwatch?.rgb ?: ResourcesCompat.getColor(
                                root.resources,
                                R.color.white,
                                null
                            )
                        )
                        bookTitle.setTextColor(
                            root.context?.getContrastColor(it) ?: ResourcesCompat.getColor(
                                root.resources,
                                R.color.white,
                                null
                            )
                        )
                        authorName.setTextColor(
                            root.context?.getContrastColor(it) ?: ResourcesCompat.getColor(
                                root.resources,
                                R.color.white,
                                null
                            )
                        )
                    }
                }
            }
        }
    }
}