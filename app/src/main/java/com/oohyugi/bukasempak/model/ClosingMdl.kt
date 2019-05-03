package com.oohyugi.bukasempak.model

import com.google.gson.annotations.SerializedName

data class ClosingMdl(@SerializedName("closed")
                      val closed: Boolean = false)