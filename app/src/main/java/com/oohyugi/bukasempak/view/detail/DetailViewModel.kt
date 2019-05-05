package com.oohyugi.bukasempak.view.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import android.util.Log
import com.google.gson.Gson
import com.oohyugi.bukasempak.api.base.MyResult
import com.oohyugi.bukasempak.api.repo.RepoImpl
import com.oohyugi.bukasempak.base.BaseViewModel
import com.oohyugi.bukasempak.model.*
import com.oohyugi.bukasempak.view.home.HomeViewModel
import kotlinx.coroutines.*

/**
 * Created by oohyugi on 2019-05-01.
 * github: https://github.com/oohyugi
 */
class DetailViewModel : BaseViewModel() {
    private val tokenLiveData = HomeViewModel.NonNullMediatorLiveData<String>()
    private val listReviewLiveData = HomeViewModel.NonNullMediatorLiveData<BaseMdl<List<ReviewProductMdl>>>()
    private val listSimilarLiveData = HomeViewModel.NonNullMediatorLiveData<BaseMdl<List<ProductMdl>>>()
    private var userToken  = MediatorLiveData<String>()
    private var limit  = MediatorLiveData<Int>()
    private var productId  = MediatorLiveData<String>()

    val mToken: LiveData<String> get() = tokenLiveData
    val mListReviewMdl: LiveData<BaseMdl<List<ReviewProductMdl>>> get() = listReviewLiveData
    val mListProductMdl: LiveData<BaseMdl<List<ProductMdl>>> get() = listSimilarLiveData

    private fun tokenCall() {
        setProgress.postValue(true)
        scope.launch {

            when (val result = repo.getToken()) {

                is MyResult.Success ->{
                    setProgress.postValue(false)
                    tokenLiveData.postValue(result.data)

                }
                is MyResult.Error -> {
                    errorApi.postValue(result.exception.message)
                    Log.e("ErrorToken", result.exception.message)
                }

            }



        }
    }

    fun reviewProductCall(userToken:String,productId:String,limit:Int) {
//        setProgress.postValue(true)
        scope.launch {

            when (val result = repo.getReviewProduct(userToken, productId, limit)) {

                is MyResult.Success ->{
//                    setProgress.postValue(false)
                    listReviewLiveData.postValue(result.data)

                }
                is MyResult.Error -> {
                    errorApi.postValue(result.exception.message)
                    Log.e("ErrorToken", result.exception.message)
                }

            }



        }
    }

    fun similarProductCall(userToken:String,productId:String) {
//        setProgress.postValue(true)
        scope.launch {

            when (val result = repo.getSimilarProduct(userToken, productId)) {

                is MyResult.Success ->{
//                    setProgress.postValue(false)
                    listSimilarLiveData.postValue(result.data)

                }
                is MyResult.Error -> {
                    errorApi.postValue(result.exception.message)
                    Log.e("ErrorToken", result.exception.message)
                }

            }



        }
    }




    override fun onCleared() {
        super.onCleared()
        myJob?.cancel()
    }
}
