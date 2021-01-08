package com.example.capstone.api

import com.example.capstone.model.Quote
import retrofit2.http.GET

interface QuoteApiService {

    @GET("/random")
    suspend fun getQuoteOfTheDay(): Quote
}