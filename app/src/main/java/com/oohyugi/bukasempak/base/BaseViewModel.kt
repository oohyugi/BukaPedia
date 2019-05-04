package com.oohyugi.bukasempak.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import com.oohyugi.bukasempak.api.repo.RepoImpl
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext

/**
 * Created by oohyugi on 2019-05-01.
 * github: https://github.com/oohyugi
 */
open class BaseViewModel : ViewModel() {

     val setProgress= MediatorLiveData<Boolean>()
    val isProgress : LiveData<Boolean> get() = setProgress
     val errorApi = MediatorLiveData<String>()
    val error: LiveData<String>? get() = errorApi
     var myJob = Job()
     var repo: RepoImpl = RepoImpl()
     val coroutineContext: CoroutineContext
        get() = myJob + Dispatchers.Default

     val scope = CoroutineScope(coroutineContext)

}