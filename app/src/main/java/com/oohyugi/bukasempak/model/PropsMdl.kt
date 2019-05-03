package com.oohyugi.bukasempak.model

import com.google.gson.annotations.SerializedName

data class PropsMdl(@SerializedName("images")
                    val images: ImagesMdl,
                    @SerializedName("url")
                    val url: String = "")