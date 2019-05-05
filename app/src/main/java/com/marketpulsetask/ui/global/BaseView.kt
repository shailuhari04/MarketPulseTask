package com.marketpulsetask.ui.global

import com.marketpulsetask.pojo.Response

interface BaseView {

    fun onSuccess(objects: List<Response>)

    fun onError(objects: String?)
}