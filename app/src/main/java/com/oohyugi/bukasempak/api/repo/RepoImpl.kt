package com.oohyugi.bukasempak.api.repo

import com.oohyugi.bukasempak.api.base.BaseRepository
import com.oohyugi.bukasempak.api.base.BaseResult
import com.oohyugi.bukasempak.api.base.MyResult
import com.oohyugi.bukasempak.api.base.MyResult.Success
import com.oohyugi.bukasempak.model.*
import kotlinx.coroutines.Deferred
import retrofit2.Response
import java.io.IOException
import java.lang.Exception

/**
 * Created by oohyugi on 2019-05-01.
 * github: https://github.com/oohyugi
 */
class RepoImpl : BaseRepository(), RepoReq {
    override suspend fun getSimilarProduct(token: String, productId: String): MyResult<BaseMdl<List<ProductMdl>>>? {
        try {
            val response = apiClientBl.getSimilarProduct(productId,token).await()
            if (response.isSuccessful){

                return Success<BaseMdl<List<ProductMdl>>>(response.body()!!)
            }
            return MyResult.Error(IOException(response.message()))

        }catch (e : Exception){
            return MyResult.Error(e)
        }
    }

    override suspend fun getReviewProduct(token: String, productId: String, limit: Int): MyResult<BaseMdl<List<ReviewProductMdl>>>? {

        try {
            val response = apiClientBl.getReviewProduct(token,productId,limit).await()
            if (response.isSuccessful){

                return Success<BaseMdl<List<ReviewProductMdl>>>(response.body()!!)
            }
            return MyResult.Error(IOException(response.message()))

        }catch (e : Exception){
            return MyResult.Error(e)
        }
    }

    override suspend fun getToken(): MyResult<String> {
        try {
            val response = apiClient.getToken().await()
            if (response.isSuccessful){

                return Success<String>(response.body()?.token!!)
            }
            return MyResult.Error(IOException(response.message()))

        }catch (e : Exception){
            return MyResult.Error(e)
        }


    }

    override suspend fun getBanner() = safeApiCall(
        { getBanneApi()},
        errorMessage = "error"


        )

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


    private suspend fun getBanneApi(): MyResult<List<BannerMdl>> {
        val response = apiClient.getBannerApi().await()
            if (response.isSuccessful){
                return Success<List<BannerMdl>>(response.body()?.data!!)
            }
            return MyResult.Error(IOException(response.message()))


    }


    private suspend fun getHomeApi(): MyResult<List<BLHomeMdl>> {

        try {
            val response = apiClient.getHomeApi().await()
            if (response.isSuccessful){

                return Success<List<BLHomeMdl>>(response.body()?.data!!)
            }
            return MyResult.Error(IOException(response.message()))

        }catch (e : Exception){
            return MyResult.Error(e)
        }




    }
    private suspend fun getMenuAPi(): MyResult<List<MenuItemMdl>> {

        try {
            val response = apiClient.getMenuApi().await()
            if (response.isSuccessful){
                return Success<List<MenuItemMdl>>(response.body()?.data!!)
            }
            return MyResult.Error(IOException(response.message()))

        }catch (e : Exception){
            return MyResult.Error(e)
        }

    }



    private suspend fun getFlashDealApi(): MyResult<BaseFlashDealMdl> {

        try {
            val response = apiClient.getFlashDealApi().await()
            if (response.isSuccessful){
                return Success<BaseFlashDealMdl>(response.body()!!)
            }
            return MyResult.Error(IOException(response.message()))

        }catch (e : Exception){
            return MyResult.Error(e)

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