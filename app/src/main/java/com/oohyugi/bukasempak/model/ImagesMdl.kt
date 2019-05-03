package com.oohyugi.bukasempak.model

import com.google.gson.annotations.SerializedName

data class ImagesMdl(@SerializedName("large_urls")
                     val largeUrls: List<String>?,
                     @SerializedName("small_urls")
                     val smallUrls: List<String>?)