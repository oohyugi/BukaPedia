package com.oohyugi.bukasempak.view.home

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MediatorLiveData
import android.arch.lifecycle.ViewModel;
import android.util.Log
import com.google.gson.Gson
import com.oohyugi.bukasempak.api.base.MyResult
import com.oohyugi.bukasempak.api.repo.RepoImpl
import com.oohyugi.bukasempak.model.*
import com.oohyugi.bukasempak.view.main.BaseViewModel
import kotlinx.coroutines.*

class HomeViewModel : BaseViewModel() {
    private var myJob: Job? = null
    private lateinit var repo: RepoImpl
    private  val flashdealApi=NonNullMediatorLiveData<BaseFlashDealMdl>()
    private  val errorApi= NonNullMediatorLiveData<String>()
    private val homeApi = NonNullMediatorLiveData<List<BLHomeMdl>>()
    private val menuApi = NonNullMediatorLiveData<List<MenuItemMdl>>()
    private val bannerApi = NonNullMediatorLiveData<List<BannerMdl>>()



    val flashDealMdl: LiveData<BaseFlashDealMdl> get() = flashdealApi
    val error : LiveData<String>? get() = errorApi
    val homeMdl : LiveData<List<BLHomeMdl>> get() = homeApi
    val menuMdl : LiveData<List<MenuItemMdl>> get() = menuApi
    val bannerMdl : LiveData<List<BannerMdl>> get() = bannerApi

    init {

        repo = RepoImpl()

        homeCall()
        flashDealCall()
        bannerCall()
        menuCall()

    }

    private fun bannerCall() {
        myJob = CoroutineScope(Dispatchers.IO).launch {
            val result = repo.getBanner()
            withContext(Dispatchers.Main) {
                //do something with result

                when(result){
                    is MyResult.Success-> {
                        Log.e("response", Gson().toJson(result))
                        bannerApi.postValue(result.data)
                    }
                    is MyResult.Error-> errorApi.postValue(result.exception.message)
                }



            }
        }
    }

    private fun menuCall() {
        myJob = CoroutineScope(Dispatchers.IO).launch {
            val result = repo.getMenu()
            withContext(Dispatchers.Main) {
                //do something with result

                when(result){
                    is MyResult.Success-> {
                        Log.e("response", Gson().toJson(result))
                        menuApi.postValue(result.data)
                    }
                    is MyResult.Error-> errorApi.postValue(result.exception.message)
                }



            }
        }
    }

    private fun homeCall() {
        setProgress.postValue(true)
        myJob = CoroutineScope(Dispatchers.IO).launch {
            val result = repo.getHome()
            withContext(Dispatchers.Main){
                setProgress.postValue(false)
                when(result){
                    is MyResult.Success-> {
                        Log.e("response",result.data.size.toString())
                        homeApi.postValue(result.data)

                    }
                    is MyResult.Error->{
                        errorApi.postValue(result.exception.message)
                        Log.e("response",result.exception.message)
                    }
                }
            }
        }

    }

    fun flashDealCall(){

        myJob = CoroutineScope(Dispatchers.IO).launch {
            val result = repo.getFlashDeal()
            withContext(Dispatchers.Main) {
                //do something with result

                when(result){
                    is MyResult.Success-> {
                        Log.e("response", Gson().toJson(result))
                        flashdealApi.postValue(result.data)
                    }
                    is MyResult.Error-> errorApi.postValue(result.exception.message)
                }



            }
        }
    }

    class NonNullMediatorLiveData<T> : MediatorLiveData<T>()
    fun onDestroy(){
        myJob?.cancel()
    }

    override fun onCleared() {
        super.onCleared()
        myJob?.cancel()
    }


}
