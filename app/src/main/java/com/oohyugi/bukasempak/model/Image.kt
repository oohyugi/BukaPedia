package com.oohyugi.bukasempak.model

import com.google.gson.annotations.SerializedName

data class Image(@SerializedName("desktop_url")
                 val desktopUrl: String = "",
                 @SerializedName("mobile_url")
                 val mobileUrl: String = "",
                 @SerializedName("new_mobile_url")
                 val newMobileUrl: String = "")