package com.oohyugi.bukasempak.model

import com.google.gson.annotations.SerializedName

data class RejectionMdl(@SerializedName("recent_transactions")
                        val recentTransactions: Int = 0,
                        @SerializedName("rejected")
                        val rejected: Int = 0)