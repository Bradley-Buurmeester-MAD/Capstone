package com.example.capstone.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.capstone.api.QuoteApi
import com.example.capstone.api.QuoteApiService
import com.example.capstone.model.Quote
import kotlinx.coroutines.withTimeout

class QuoteRepository {
    private val quoteApiService: QuoteApiService = QuoteApi.createApi()

    private val _quote: MutableLiveData<Quote> = MutableLiveData()

    /**
     * Expose non MutableLiveData via getter
     */
    val quote: LiveData<Quote>
        get() = _quote

    /**
     * suspend function that calls a suspend function
     */
    suspend fun getQuoteOfTheDay()  {
        try {
            //timeout the request after 5 seconds
            val result = withTimeout(5_000) {
                quoteApiService.getQuoteOfTheDay()
            }

            _quote.value = result
        } catch (error: Throwable) {
            throw QuoteRefreshError("Unable to refresh quote", error)
        }
    }

    class QuoteRefreshError(message: String, cause: Throwable) : Throwable(message, cause)

}