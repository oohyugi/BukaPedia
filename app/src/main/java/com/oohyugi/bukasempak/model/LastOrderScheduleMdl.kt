package com.oohyugi.bukasempak.model

import com.google.gson.annotations.SerializedName

data class LastOrderScheduleMdl(@SerializedName("saturday")
                                val saturday: String = "",
                                @SerializedName("tuesday")
                                val tuesday: String = "",
                                @SerializedName("friday")
                                val friday: String = "",
                                @SerializedName("thursday")
                                val thursday: String = "",
                                @SerializedName("wednesday")
                                val wednesday: String = "",
                                @SerializedName("monday")
                                val monday: String = "")