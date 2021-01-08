package com.example.capstone.model

import com.google.gson.annotations.SerializedName

data class Quote(
    @SerializedName("content") var message: String,
    @SerializedName("author") var author: String
)
