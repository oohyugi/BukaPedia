package com.oohyugi.bukasempak.model

import com.google.gson.annotations.SerializedName

data class VotesMdl(@SerializedName("user_vote")
                    val userVote: String = "",
                    @SerializedName("negative_votes")
                    val negativeVotes: Int = 0,
                    @SerializedName("positive_votes")
                    val positiveVotes: Int = 0)