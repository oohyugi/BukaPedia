package com.oohyugi.bukasempak.model

import com.google.gson.annotations.SerializedName

data class AddressMdl(@SerializedName("province")
                      val province: String = "",
                      @SerializedName("city")
                      val city: String = "")