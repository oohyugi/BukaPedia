package com.oohyugi.bukasempak.model

import com.google.gson.annotations.SerializedName

data class OtherFlashDealItemMdl(@SerializedName("end_date")
                                 val endDate: String = "",
                                 @SerializedName("name")
                                 val name: String = "",
                                 @SerializedName("max_purchase")
                                 val maxPurchase: Int = 0,
                                 @SerializedName("id")
                                 val id: Int = 0,
                                 @SerializedName("state")
                                 val state: String = "",
                                 @SerializedName("seo")
                                 val seo: String = "",
                                 @SerializedName("start_date")
                                 val startDate: String = "")