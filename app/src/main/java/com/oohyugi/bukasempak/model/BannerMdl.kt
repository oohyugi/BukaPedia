package com.oohyugi.bukasempak.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class BannerMdl(@SerializedName("image")
                     val image: Image,
                     @SerializedName("name")
                     val name: String = "",
                     @SerializedName("details")
                     val details: Details,
                     @SerializedName("id")
                     val id: Int = 0): Serializable