package com.oohyugi.bukasempak.model

import com.google.gson.annotations.SerializedName

data class Details(@SerializedName("url_type")
                   val urlType: String = "",
                   @SerializedName("url")
                   val url: String = "")