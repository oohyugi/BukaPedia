package com.oohyugi.bukasempak.model

import com.google.gson.annotations.SerializedName

data class ItemsFlashDealMdl(@SerializedName("end_date")
                     val endDate: String = "",
                             @SerializedName("original_price")
                     val originalPrice: Int = 0,
                             @SerializedName("images")
                     val images: List<String>?,
                             @SerializedName("city")
                     val city: String = "",
                             @SerializedName("discount_price")
                     val discountPrice: Int = 0,
                             @SerializedName("rating")
                     val rating: RatingMdl,
                             @SerializedName("product_id")
                     val productId: String = "",
                             @SerializedName("percentage")
                     val percentage: Int = 0,
                             @SerializedName("product_sku_id")
                     val productSkuId: Long = 0,
                             @SerializedName("name")
                     val name: String = "",
                             @SerializedName("flash_deal_stock")
                     val flashDealStock: Int = 0,
                             @SerializedName("id")
                     val id: Int = 0,
                             @SerializedName("state")
                     val state: String = "",
                             @SerializedName("stock")
                     val stock: Int = 0,
                             @SerializedName("brand")
                     val brand: Boolean = false,
                             @SerializedName("start_date")
                     val startDate: String = "")