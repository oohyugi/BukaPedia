package com.oohyugi.bukasempak.model

import com.google.gson.annotations.SerializedName

data class MetaMdl(@SerializedName("total")
                   val total: Int = 0,
                   @SerializedName("limit")
                   val limit: String = "",
                   @SerializedName("http_status")
                   val httpStatus: Int = 0)