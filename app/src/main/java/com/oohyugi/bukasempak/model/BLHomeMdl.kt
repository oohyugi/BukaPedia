package com.oohyugi.bukasempak.model

import com.google.gson.annotations.SerializedName

data class BLHomeMdl(@SerializedName("image")
                      val image: ImagesMobileMdl? = null,
                     @SerializedName("total")
                      val total: Int = 0,
                     @SerializedName("page_title")
                      val pageTitle: String = "",
                     @SerializedName("subtitles")
                      val subtitles: List<String>,
                     @SerializedName("type")
                     var type: String = "",
                     @SerializedName("title")
                      val title: String = "",
                     @SerializedName("homepage_image_url")
                     val homePageImgUrl: String? = null,
                     @SerializedName("url")
                      val url: String = "",
                     @SerializedName("campaign_id")
                      val campaignId: Int = 0,
                     @SerializedName("order")
                      val order: Int = 0,
                     var flashDeal: BaseFlashDealMdl? = null,
                     @SerializedName("products")
                      val products: List<ProductsItemMdl>?)