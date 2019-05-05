package com.oohyugi.bukasempak.model

import com.google.gson.annotations.SerializedName

/**
 * Created by oohyugi on 2019-05-01.
 * github: https://github.com/oohyugi
 */
data class BaseMdl<T> (
    @SerializedName("data")
    val data :T,
    @SerializedName("meta")
    val meta :MetaMdl? = null
)