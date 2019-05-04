package com.oohyugi.bukasempak.view.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import android.util.Log
import com.google.gson.Gson
import com.oohyugi.bukasempak.api.base.MyResult
import com.oohyugi.bukasempak.api.repo.RepoImpl
import com.oohyugi.bukasempak.model.*
import com.oohyugi.bukasempak.base.BaseViewModel
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class HomeViewModel : BaseViewModel() {

    private val flashdealApi = NonNullMediatorLiveData<BaseFlashDealMdl>()
    private val homeApi = NonNullMediatorLiveData<List<BLHomeMdl>>()
    private val menuApi = NonNullMediatorLiveData<List<MenuItemMdl>>()
    private val bannerApi = NonNullMediatorLiveData<List<BannerMdl>>()
    private val tokenApi = NonNullMediatorLiveData<String>()


    val flashDealMdl: LiveData<BaseFlashDealMdl> get() = flashdealApi

    val homeMdl: LiveData<List<BLHomeMdl>> get() = homeApi
    val menuMdl: LiveData<List<MenuItemMdl>> get() = menuApi
    val bannerMdl: LiveData<List<BannerMdl>> get() = bannerApi
    val mToken: LiveData<String> get() = tokenApi

    init {
        flashDealCall()
        bannerCall()
        menuCall()
        homeCall()

        tokenCall()
    }

    private fun tokenCall() {
        scope.launch {
            when (val result = repo.getToken()) {
                is MyResult.Success ->
                    tokenApi.postValue(result.data)
                is MyResult.Error -> {
                    errorApi.postValue(result.exception.message)
                    Log.e("ErrorToken", result.exception.message)
                }

            }


        }
    }

    private fun bannerCall() {
        scope.launch {
            when (val result = repo.getBanner()) {
                is MyResult.Success ->
                    bannerApi.postValue(result.data)
                is MyResult.Error -> {
                    errorApi.postValue(result.exception.message)
                    Log.e("Error", result.exception.message)
                }

            }


        }
    }

    private fun menuCall() {

        scope.launch {
            when (val result = repo.getMenu()) {
                is MyResult.Success ->
                    menuApi.postValue(result.data)
                is MyResult.Error -> {
                    errorApi.postValue(result.exception.message)
                    Log.e("Error", result.exception.message)

                }

            }


        }

    }

    private fun homeCall() {
        setProgress.postValue(true)

        scope.launch {
            val result = repo.getHome()

            setProgress.postValue(false)
            when (result) {
                is MyResult.Success -> {
                    Log.e("response", result.data.size.toString())
                    homeApi.postValue(result.data)

                }
                is MyResult.Error -> {
                    errorApi.postValue(result.exception.message)
                    Log.e("response", result.exception.message)
                }
            }


        }


    }

    fun flashDealCall() {

        scope.launch {
            val result = repo.getFlashDeal()

            when (result) {
                is MyResult.Success -> {

                    flashdealApi.postValue(result.data)
                }
                is MyResult.Error -> errorApi.postValue(result.exception.message)
            }


        }
    }

    class NonNullMediatorLiveData<T> : MediatorLiveData<T>()

    fun onDestroy() {
        myJob.cancel()
    }

    override fun onCleared() {
        super.onCleared()
        myJob.cancel()
    }


}
