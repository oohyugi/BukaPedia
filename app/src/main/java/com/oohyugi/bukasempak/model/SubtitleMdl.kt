package com.oohyugi.bukasempak.model

import com.google.gson.annotations.SerializedName

data class SubtitleMdl(@SerializedName("entities")
                       val entities: List<EntitiesItemMdl>?,
                       @SerializedName("text")
                       val text: String = "")