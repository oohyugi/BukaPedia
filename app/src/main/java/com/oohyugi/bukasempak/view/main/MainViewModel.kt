package com.oohyugi.bukasempak.view.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import android.util.Log
import com.google.gson.Gson
import com.oohyugi.bukasempak.api.base.MyResult
import com.oohyugi.bukasempak.api.repo.RepoImpl
import com.oohyugi.bukasempak.model.BLHomeMdl
import com.oohyugi.bukasempak.model.BaseFlashDealMdl
import kotlinx.coroutines.*

/**
 * Created by oohyugi on 2019-05-01.
 * github: https://github.com/oohyugi
 */
class MainViewModel : ViewModel() {

    private var myJob: Job? = null
    private lateinit var repo:RepoImpl
    private  val flashdealMdl=NonNullMediatorLiveData<BaseFlashDealMdl>()
    private  val errorApi= NonNullMediatorLiveData<String>()
    private val homeApi = NonNullMediatorLiveData<List<BLHomeMdl>>()



    val flashDeal: LiveData<BaseFlashDealMdl> get() = flashdealMdl
    val error : LiveData<String>? get() = errorApi
    val homeMdl : LiveData<List<BLHomeMdl>> get() = homeApi

    init {

        repo = RepoImpl()
//        flashDealCall()
//        homeCall()

    }

    private fun homeCall() {
        myJob = CoroutineScope(Dispatchers.IO).launch {
            val result = repo.getHome()
            withContext(Dispatchers.Default){
                when(result){
                    is MyResult.Success-> {
                        Log.e("response",Gson().toJson(result))
                        homeApi.postValue(result.data)
                    }
                    is MyResult.Error-> errorApi.postValue(result.exception.message)
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
                        Log.e("response",Gson().toJson(result))
                        flashdealMdl.postValue(result.data)
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
