package com.example.capstone.ui.quote

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.capstone.R
import kotlinx.android.synthetic.main.fragment_quote.*

class QuoteFragment : Fragment() {
    private val viewModel: QuoteViewModel by viewModels()

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_quote, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeQuote()
        viewModel.getRandomQuote()
    }

    private fun observeQuote() {
        viewModel.quote.observe(viewLifecycleOwner, Observer { quote ->
            tvQuote.text = quote.message
            tvAuthor.text = quote.author
        })
    }
}