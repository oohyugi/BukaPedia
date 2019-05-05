package com.oohyugi.bukasempak.model

import com.google.gson.annotations.SerializedName

data class ProductreviewMdl(@SerializedName("image_url")
                            val imageUrl: String = "",
                            @SerializedName("name")
                            val name: String = "",
                            @SerializedName("id")
                            val id: String = "",
                            @SerializedName("url")
                            val url: String = "")