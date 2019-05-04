package com.oohyugi.bukasempak.api

import com.oohyugi.bukasempak.model.*
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import java.util.*

/**
 * Created by oohyugi on 2019-05-01.
 * github: https://github.com/oohyugi
 */
interface ApiServices {


    @GET("bl_flash_deal_new.json")
    fun getFlashDealApi():Deferred<Response<BaseFlashDealMdl>>


    @GET("bl_home_new.json")
    fun getHomeApi():Deferred<Response<BaseMdl<List<BLHomeMdl>>>>

    @GET("bl_menu.json")
    fun getMenuApi():Deferred<Response<BaseMdl<List<MenuItemMdl>>>>

    @GET("bl_banner.json")
    fun getBannerApi():Deferred<Response<BaseMdl<List<BannerMdl>>>>

    @GET("bl_token.php")
    fun getToken():Deferred<Response<TokenMdl>>
}