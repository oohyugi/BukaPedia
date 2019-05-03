package com.oohyugi.bukasempak.model

import com.google.gson.annotations.SerializedName

data class RatingMdl(@SerializedName("user_count")
                     val userCount: Int = 0,
                     @SerializedName("average_rate")
                     val averageRate: Double = 0.0)