package com.oohyugi.bukasempak.model

import com.google.gson.annotations.SerializedName

data class SlaMdl(@SerializedName("type")
                  val type: String = "",
                  @SerializedName("value")
                  val value: Int = 0)