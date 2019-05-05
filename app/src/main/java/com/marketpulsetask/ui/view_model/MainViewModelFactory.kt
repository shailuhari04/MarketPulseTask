package com.marketpulsetask.ui.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.marketpulsetask.ui.global.BaseView

class MainViewModelFactory(val view: BaseView) : ViewModelProvider.NewInstanceFactory() {


    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainViewModel(view) as T
    }
}