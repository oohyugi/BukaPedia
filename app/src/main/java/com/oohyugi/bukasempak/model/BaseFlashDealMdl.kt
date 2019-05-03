package com.oohyugi.bukasempak.model

import com.google.gson.annotations.SerializedName

data class BaseFlashDealMdl(@SerializedName("flash_deal")
                            val flashDeal: FlashDealmdl,
                            @SerializedName("other_flash_deal")
                            val otherFlashDeal: List<OtherFlashDealItemMdl>?,
                            @SerializedName("time")
                            val time: String = "",
                            @SerializedName("message")
                            val message: String? = null,
                            @SerializedName("status")
                            val status: String = "")