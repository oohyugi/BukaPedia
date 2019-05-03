package com.oohyugi.bukasempak.model

import com.google.gson.annotations.SerializedName

data class LevelMdl(@SerializedName("image_url")
                    val imageUrl: String = "",
                    @SerializedName("name")
                    val name: String = "")