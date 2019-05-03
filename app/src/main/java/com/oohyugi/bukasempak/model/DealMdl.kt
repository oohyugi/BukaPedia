package com.oohyugi.bukasempak.model

import com.google.gson.annotations.SerializedName

data class DealMdl(@SerializedName("original_price")
                   val originalPrice: Int = 0,
                   @SerializedName("discount_price")
                   val discountPrice: Int = 0,
                   @SerializedName("percentage")
                   val percentage: Int = 0,
                   @SerializedName("applied_date")
                   val appliedDate: String = "",
                   @SerializedName("expired_date")
                   val expiredDate: String = "")