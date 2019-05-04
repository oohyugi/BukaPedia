package com.oohyugi.bukasempak.view.splash

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import android.util.Log
import com.google.gson.Gson
import com.oohyugi.bukasempak.api.base.MyResult
import com.oohyugi.bukasempak.api.repo.RepoImpl
import com.oohyugi.bukasempak.base.BaseViewModel
import com.oohyugi.bukasempak.model.BLHomeMdl
import com.oohyugi.bukasempak.model.BaseFlashDealMdl
import com.oohyugi.bukasempak.view.home.HomeViewModel
import kotlinx.coroutines.*

/**
 * Created by oohyugi on 2019-05-01.
 * github: https://github.com/oohyugi
 */
class SplashViewModel : BaseViewModel() {
    private val tokenLiveData = HomeViewModel.NonNullMediatorLiveData<String>()

    val mToken: LiveData<String> get() = tokenLiveData
    init {

        tokenCall()
    }

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




    override fun onCleared() {
        super.onCleared()
        myJob?.cancel()
    }
}
