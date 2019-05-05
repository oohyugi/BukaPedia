package com.oohyugi.bukasempak.model

import com.google.gson.annotations.SerializedName

data class ReviewMdl(@SerializedName("rate")
                     val rate: Int = 0,
                     @SerializedName("anonym")
                     val anonym: Boolean = false,
                     @SerializedName("title")
                     val title: String = "",
                     @SerializedName("content")
                     val content: String = "")