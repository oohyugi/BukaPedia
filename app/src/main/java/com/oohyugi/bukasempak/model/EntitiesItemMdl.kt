package com.oohyugi.bukasempak.model

import com.google.gson.annotations.SerializedName

data class EntitiesItemMdl(@SerializedName("offset")
                           val offset: Int = 0,
                           @SerializedName("length")
                           val length: Int = 0,
                           @SerializedName("type")
                           val type: String = "",
                           @SerializedName("props")
                           val props: PropsMdl)