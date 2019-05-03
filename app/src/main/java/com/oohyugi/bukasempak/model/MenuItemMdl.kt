package com.oohyugi.bukasempak.model

import com.google.gson.annotations.SerializedName

data class MenuItemMdl(@SerializedName("icon_url")
                       val iconUrl: String = "",
                       @SerializedName("promo")
                       val promo: Boolean = false,
                       @SerializedName("ordering")
                       val ordering: Int = 0,
                       @SerializedName("force_desktop_view")
                       val forceDesktopView: Boolean = false,
                       @SerializedName("new_feature")
                       val newFeature: Boolean = false,
                       @SerializedName("name")
                       val name: String = "",
                       @SerializedName("display_name")
                       val displayName: String = "",
                       @SerializedName("category")
                       val category: String = "",
                       @SerializedName("apps_event_name")
                       val appsEventName: String = "",
                       @SerializedName("url")
                       val url: String = "")