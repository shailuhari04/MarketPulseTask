package com.marketpulsetask.pojo

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


data class CriteriaItem(
    val text: String? = null,
    val type: String? = null,
    @SerializedName("variable")
    @Expose
    val variable: HashMap<String, VariableItems>? = null


) {
    override fun toString(): String {
        return "CriteriaItem(text=$text, type=$type, variable=$variable)"
    }
}
