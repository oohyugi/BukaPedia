package com.oohyugi.bukasempak.model

import com.google.gson.annotations.SerializedName

data class ProductsItemMdl(@SerializedName("product")
                           val product: ProductMdl,
                           @SerializedName("source")
                           val source: String = "",
                           @SerializedName("reference_type")
                           val referenceType: String = "")