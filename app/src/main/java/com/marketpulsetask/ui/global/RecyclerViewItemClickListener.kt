package com.marketpulsetask.ui.global

import com.marketpulsetask.pojo.CriteriaItem
import com.marketpulsetask.pojo.VariableItems

interface RecyclerViewItemClickListener {
    fun parentItemClicked(criteriaData: MutableList<CriteriaItem>, position: Int)
    fun variableItemClicked(values: List<Float>, position: Int)
    fun criteriaItemClicked(variable: HashMap<String, VariableItems>, position: Int)
}