package com.oohyugi.bukasempak.model

import com.google.gson.annotations.SerializedName

data class ReviewProductMdl(@SerializedName("product")
                            val product: ProductreviewMdl,
                            @SerializedName("updated_at")
                            val updatedAt: String = "",
                            @SerializedName("sender")
                            val sender: SenderMdl,
                            @SerializedName("review")
                            val review: ReviewMdl,
                            @SerializedName("created_at")
                            val createdAt: String = "",
                            @SerializedName("votes")
                            val votes: VotesMdl,
                            @SerializedName("id")
                            val id: Int = 0)