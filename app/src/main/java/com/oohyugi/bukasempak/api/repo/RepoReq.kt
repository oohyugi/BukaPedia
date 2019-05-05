package com.oohyugi.bukasempak.api.repo

import com.oohyugi.bukasempak.api.base.BaseResult
import com.oohyugi.bukasempak.api.base.MyResult
import com.oohyugi.bukasempak.model.*
import kotlinx.coroutines.Deferred
import retrofit2.Response

/**
 * Created by oohyugi on 2019-05-01.
 * github: https://github.com/oohyugi
 */
interface RepoReq {

    suspend fun getFlashDeal():MyResult<BaseFlashDealMdl>
    suspend fun getHome():MyResult<List<BLHomeMdl>>
    suspend fun getMenu():MyResult<List<MenuItemMdl>>
    suspend fun getBanner():MyResult<List<BannerMdl>>
    suspend fun getToken():MyResult<String>
    suspend fun getReviewProduct(token:String,productId:String,limit:Int):MyResult<BaseMdl<List<ReviewProductMdl>>>?
    suspend fun getSimilarProduct(token:String,productId:String):MyResult<BaseMdl<List<ProductMdl>>>?

}