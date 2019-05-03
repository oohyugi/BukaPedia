package com.oohyugi.bukasempak.model

import com.google.gson.annotations.SerializedName

data class StoreMdl(@SerializedName("header_image")
                    val headerImage: HeaderImageMdl,
                    @SerializedName("address")
                    val address: AddressMdl,
                    @SerializedName("premium_top_seller")
                    val premiumTopSeller: Boolean = false,
                    @SerializedName("level")
                    val level: LevelMdl,
                    @SerializedName("inactivity")
                    val inactivity: InactivityMdl,
                    @SerializedName("term_and_condition")
                    val termAndCondition: String = "",
                    @SerializedName("description")
                    val description: String = "",
                    @SerializedName("sla")
                    val sla: SlaMdl,
                    @SerializedName("carriers")
                    val carriers: List<String>?,
                    @SerializedName("delivery_time")
                    val deliveryTime: String = "",
                    @SerializedName("brand_seller")
                    val brandSeller: Boolean = false,
                    @SerializedName("rejection")
                    val rejection: RejectionMdl,
                    @SerializedName("url")
                    val url: String = "",
                    @SerializedName("last_order_schedule")
                    val lastOrderSchedule: LastOrderScheduleMdl,
                    @SerializedName("premium_level")
                    val premiumLevel: String = "",
                    @SerializedName("closing")
                    val closing: ClosingMdl,
                    @SerializedName("avatar_url")
                    val avatarUrl: String = "",
                    @SerializedName("reviews")
                    val reviews: ReviewsMdl,
                    @SerializedName("subscriber_amount")
                    val subscriberAmount: Int = 0,
                    @SerializedName("alert")
                    val alert: String = "",
                    @SerializedName("name")
                    val name: String = "",
                    @SerializedName("id")
                    val id: Int = 0)