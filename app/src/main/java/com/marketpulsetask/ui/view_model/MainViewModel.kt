package com.marketpulsetask.ui.view_model

import android.annotation.SuppressLint
import android.view.View
import androidx.databinding.ObservableInt
import androidx.lifecycle.ViewModel
import com.marketpulsetask.pojo.Response
import com.marketpulsetask.ui.global.BaseView
import com.marketpulsetask.ui.model.MainModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

open class MainViewModel(val view: BaseView) : ViewModel() {

    lateinit var loading: ObservableInt
    lateinit var showEmpty: ObservableInt
    private var model: MainModel = MainModel()

    fun init() {
        loading = ObservableInt()
        showEmpty = ObservableInt()
        showEmpty.set(View.GONE)
    }

    @SuppressLint("CheckResult")
    fun loadData() {
        model.fetchData()!!.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
            .subscribe(this::handleResponse, this::handleError)
    }

    private fun handleResponse(dataList: List<Response>) {
        loading.set(View.GONE)
        view.onSuccess(dataList)
    }

    private fun handleError(error: Throwable) {
        loading.set(View.GONE)
        view.onError(error.message)
    }
}