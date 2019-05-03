package com.oohyugi.bukasempak.model

import com.google.gson.annotations.SerializedName

data class HomeMdl(@SerializedName("sub_title")
                   val subTitle: String = "",
                   @SerializedName("img_url")
                   val imgUrl: String = "",
                   @SerializedName("id")
                   val id: Int = 0,
                   @SerializedName("type")
                   val type: String = "",
                   @SerializedName("type_items")
                   val typeItems: String = "",
                   @SerializedName("title")
                   val title: String = "",
                   @SerializedName("background_color")
                   val bgColor: String? = null,
                   @SerializedName("headline")
                   val headline: String? = null,
                   @SerializedName("items")
                   val items: List<ItemsMdl>?)