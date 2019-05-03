package com.oohyugi.bukasempak.model

import com.google.gson.annotations.SerializedName

data class StatsMdl(@SerializedName("interest_count")
                    val interestCount: Int = 0,
                    @SerializedName("waiting_payment_count")
                    val waitingPaymentCount: Int = 0,
                    @SerializedName("view_count")
                    val viewCount: Int = 0,
                    @SerializedName("sold_count")
                    val soldCount: Int = 0)