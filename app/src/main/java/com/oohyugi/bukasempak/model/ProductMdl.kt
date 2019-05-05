package com.oohyugi.bukasempak.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class ProductMdl(@SerializedName("max_quantity")
                      val maxQuantity: Int = 0,
                      @SerializedName("rating")
                      val rating: RatingMdl,
                      @SerializedName("created_at")
                      val createdAt: String = "",
                      @SerializedName("description")
                      val description: String = "",
                      @SerializedName("relisted_at")
                      val relistedAt: String = "",
                      @SerializedName("video_url")
                      val videoUrl: String = "",
                      @SerializedName("min_quantity")
                      val minQuantity: Int = 0,
                      @SerializedName("shipping")
                      val shipping: ShippingMdl,
                      @SerializedName("stats")
                      val stats: StatsMdl,
                      @SerializedName("price")
                      val price: Int = 0,
                      @SerializedName("imported")
                      val imported: Boolean = false,
                      @SerializedName("id")
                      val id: String = "",
                      @SerializedName("state")
                      val state: String = "",
                      @SerializedName("stock")
                      val stock: Int = 0,
                      @SerializedName("images")
                      val images: ImagesMdl,
                      @SerializedName("active")
                      val active: Boolean = false,
                      @SerializedName("sla")
                      val sla: SlaMdl,
                      @SerializedName("weight")
                      val weight: Int = 0,
                      @SerializedName("store")
                      val store: StoreMdl,
                      @SerializedName("rush_delivery")
                      val rushDelivery: Boolean = false,
                      @SerializedName("url")
                      val url: String = "",
                      @SerializedName("assurance")
                      val assurance: Boolean = false,
                      @SerializedName("condition")
                      val condition: String = "",
                      @SerializedName("name")
                      val name: String = "",
                      @SerializedName("for_sale")
                      val forSale: Boolean = false,
                      @SerializedName("category")
                      val category: CategoryMdl,
                      @SerializedName("deal")
                      val deal: DealMdl? = DealMdl(),
                      @SerializedName("specs")
                      val specs: Map<String,Any>? = mapOf()
                      ) : Serializable