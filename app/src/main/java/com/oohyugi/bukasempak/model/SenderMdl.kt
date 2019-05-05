package com.oohyugi.bukasempak.model

import com.google.gson.annotations.SerializedName

data class SenderMdl(@SerializedName("avatar_url")
                     val avatarUrl: String = "",
                     @SerializedName("name")
                     val name: String = "",
                     @SerializedName("id")
                     val id: Int = 0,
                     @SerializedName("type")
                     val type: String = "",
                     @SerializedName("url")
                     val url: String = "")