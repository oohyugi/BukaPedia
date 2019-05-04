package com.oohyugi.bukasempak.utils

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.oohyugi.bukasempak.model.BLHomeMdl
import com.oohyugi.bukasempak.model.BannerMdl
import com.oohyugi.bukasempak.model.BaseFlashDealMdl
import com.oohyugi.bukasempak.model.MenuItemMdl

/**
 * Created by oohyugi on 2019-05-04.
 * github: https://github.com/oohyugi
 */
object PrefHelper {

    private val PREF_TOKEN = "pref_token"
    private val PREF_FLASHDEAL = "pref_flashdeal"
    private val PREF_MENU = "pref_menu"
    private val PREF_PRODUCT = "pref_product"
    private val PREF_BANNER = "pref_banner"
    private fun pref(context: Context):SharedPreferences{
        return PreferenceManager.getDefaultSharedPreferences(context)
    }


    fun saveToken(context: Context,value:String){
        pref(context).edit().putString(PREF_TOKEN,value).apply()
    }

    fun getToken(context: Context):String?{
        return pref(context).getString(PREF_TOKEN,null)
    }

    fun saveListMenu(context: Context, it: List<MenuItemMdl>?) {
        pref(context).edit().putString(PREF_MENU,Gson().toJson(it)).apply()
    }
    fun getListMenu(context: Context): List<MenuItemMdl>{
        val json  = pref(context).getString(PREF_MENU,null)
        val type = object :TypeToken<List<MenuItemMdl>>(){}.type
        return if (json==null){
            mutableListOf()
        }else{
            Gson().fromJson(json,type)
        }

    }

    fun saveListProduct(context: Context, it: List<BLHomeMdl>?) {
        pref(context).edit().putString(PREF_PRODUCT,Gson().toJson(it)).apply()
    }
    fun getListProduct(context: Context): List<BLHomeMdl>{
        val json  = pref(context).getString(PREF_PRODUCT,null)
        val type = object :TypeToken<List<BLHomeMdl>>(){}.type
        return if (json==null){
            mutableListOf()
        }else{
            Gson().fromJson(json,type)
        }

    }

    fun saveListBanner(context: Context, it: List<BannerMdl>?) {
        pref(context).edit().putString(PREF_BANNER,Gson().toJson(it)).apply()
    }
    fun getListBanner(context: Context): List<BannerMdl>{
        val json  = pref(context).getString(PREF_BANNER,null)
        val type = object :TypeToken<List<BannerMdl>>(){}.type
        return if (json==null){
            mutableListOf()
        }else{
            Gson().fromJson(json,type)
        }


    }

}