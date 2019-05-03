package com.oohyugi.bukasempak.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class ItemsMdl(@SerializedName("img_url")
                    val imgUrl: String = "",
                    @SerializedName("price")
                    val price: Int = 0,
                    @SerializedName("icon")
                    val icon: String = "",
                    @SerializedName("id")
                    val id: Int = 0,
                    @SerializedName("title")
                    val title: String = ""):Serializable