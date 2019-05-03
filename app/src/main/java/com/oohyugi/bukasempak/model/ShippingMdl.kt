package com.oohyugi.bukasempak.model

import com.google.gson.annotations.SerializedName

data class ShippingMdl(@SerializedName("force_insurance")
                       val forceInsurance: Boolean = false)