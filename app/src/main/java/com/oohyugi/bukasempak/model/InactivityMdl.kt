package com.oohyugi.bukasempak.model

import com.google.gson.annotations.SerializedName

data class InactivityMdl(@SerializedName("inactive")
                         val inactive: Boolean = false,
                         @SerializedName("last_appear_at")
                         val lastAppearAt: String = "")