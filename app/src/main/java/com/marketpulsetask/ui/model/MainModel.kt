package com.marketpulsetask.ui.model

import androidx.databinding.BaseObservable
import com.marketpulsetask.pojo.Response
import com.marketpulsetask.remote.RetrofitClient
import io.reactivex.Observable

class MainModel : BaseObservable() {

    fun fetchData(): Observable<List<Response>>? {
        return RetrofitClient.getAPIService().data1
    }
}
