package com.oohyugi.bukasempak.api.repo

import com.oohyugi.bukasempak.api.base.BaseResult
import com.oohyugi.bukasempak.api.base.MyResult
import com.oohyugi.bukasempak.api.base.MyResult.Success
import com.oohyugi.bukasempak.api.base.safeApiCall
import com.oohyugi.bukasempak.model.BLHomeMdl
import com.oohyugi.bukasempak.model.BannerMdl
import com.oohyugi.bukasempak.model.BaseFlashDealMdl
import com.oohyugi.bukasempak.model.MenuItemMdl
import kotlinx.coroutines.Deferred
import retrofit2.Response
import java.io.IOException
import java.lang.Exception

/**
 * Created by oohyugi on 2019-05-01.
 * github: https://github.com/oohyugi
 */
class RepoImpl : RepoImplBase(), RepoReq {
    override suspend fun getBanner(): MyResult<List<BannerMdl>> {
        val response = apiClient.getBannerApi().await()
        try {
            if (response.isSuccessful){
                return Success<List<BannerMdl>>(response.body()?.data!!)
            }
            return MyResult.Error(IOException("Error occurred during fetching home!"))

        }catch (e : Exception){
            return MyResult.Error(IOException("Unable to fetch Home"))
        }

    }

    override suspend fun getMenu(): MyResult<List<MenuItemMdl>> {

        return getMenuAPi()
    }

    override suspend fun getHome(): MyResult<List<BLHomeMdl>> {
        return getHomeApi()
    }

    override suspend fun getFlashDeal()= safeApiCall(
        call = { getFlashDealApi() },
        errorMessage = "Error occurred"
    )




    private suspend fun getHomeApi(): MyResult<List<BLHomeMdl>> {
        val response = apiClient.getHomeApi().await()
        try {
            if (response.isSuccessful){
                return Success<List<BLHomeMdl>>(response.body()?.data!!)
            }
            return MyResult.Error(IOException("Error occurred during fetching home!"))

        }catch (e : Exception){
            return MyResult.Error(IOException("Unable to fetch Home"))
        }




    }
    private suspend fun getMenuAPi(): MyResult<List<MenuItemMdl>> {
        val response = apiClient.getMenuApi().await()
        try {
            if (response.isSuccessful){
                return Success<List<MenuItemMdl>>(response.body()?.data!!)
            }
            return MyResult.Error(IOException("Error occurred during fetching home!"))

        }catch (e : Exception){
            return MyResult.Error(IOException("Unable to fetch Home"))
        }

    }



    private suspend fun getFlashDealApi(): MyResult<BaseFlashDealMdl> {
        val response = apiClient.getFlashDealApi().await()
        try {
            if (response.isSuccessful){
                return Success<BaseFlashDealMdl>(response.body()!!)
            }
            return MyResult.Error(IOException("Error occurred during fetching flashdeal!"))

        }catch (e : Exception){
            return MyResult.Error(IOException("Unable to fetch Flashdeal"))

        }



    }
//    override suspend fun getFlashDeal(): Response<BaseFlashDealMdl> {
//        val result = apiClient.getFlashDealApi().await()
//        return result
//    }
//    override suspend fun getFlashDeal(): BaseFlashDealMdl {
//        val result =  apiClient.getFlashDealApi().await()
//
//        return result
//    }


}