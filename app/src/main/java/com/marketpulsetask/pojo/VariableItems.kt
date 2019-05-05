package com.marketpulsetask.pojo

class VariableItems(
    val default_value: Int,
    val max_value: Int,
    val min_value: Int,
    val parameter_name: String,
    val study_type: String,
    val type: String,
    val values: List<Float>


) {
    override fun toString(): String {
        return "VariableItems(default_value=$default_value, max_value=$max_value, min_value=$min_value, parameter_name='$parameter_name', study_type='$study_type', type='$type', values=$values)"
    }
}