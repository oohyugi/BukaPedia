package com.oohyugi.bukasempak.view.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

/**
 * Created by oohyugi on 2019-05-01.
 * github: https://github.com/oohyugi
 */
class MainViewModelFactory  :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>) = MainViewModel() as T
}