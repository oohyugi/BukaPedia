package com.oohyugi.bukasempak.view.main

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MediatorLiveData
import android.arch.lifecycle.ViewModel

/**
 * Created by oohyugi on 2019-05-01.
 * github: https://github.com/oohyugi
 */
open class BaseViewModel : ViewModel() {

     val setProgress= MediatorLiveData<Boolean>()
    val isProgress : LiveData<Boolean> get() = setProgress

}