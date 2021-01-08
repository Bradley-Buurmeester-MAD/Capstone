package com.example.capstone.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class QuoteApi {
    companion object {
        // The base url off the api.
        private const val baseUrl = "https://api.quotable.io/"

        /**
         * @return [TriviaApiService] The service class off the retrofit client.
         */
        fun createApi(): QuoteApiService{
            // Create an OkHttpClient to be able to make a log of the network traffic
            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .build()

            // Create the Retrofit instance
            val quoteApi = Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            return quoteApi.create(QuoteApiService::class.java)
        }
    }

}