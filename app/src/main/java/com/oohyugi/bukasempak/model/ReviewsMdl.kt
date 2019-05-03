package com.oohyugi.bukasempak.model

import com.google.gson.annotations.SerializedName

data class ReviewsMdl(@SerializedName("negative")
                      val negative: Int = 0,
                      @SerializedName("positive")
                      val positive: Int = 0)