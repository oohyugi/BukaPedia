package com.oohyugi.bukasempak.model

import com.google.gson.annotations.SerializedName

data class CategoryMdl(@SerializedName("name")
                       val name: String = "",
                       @SerializedName("id")
                       val id: Int = 0,
                       @SerializedName("structure")
                       val structure: List<String>?,
                       @SerializedName("url")
                       val url: String = "")