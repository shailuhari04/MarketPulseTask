package com.marketpulsetask.pojo

data class Response(
    val color: String? = null,
    val criteria: List<CriteriaItem?>? = null,
    val name: String? = null,
    val id: Int? = null,
    val tag: String? = null


) {
    override fun toString(): String {
        return "Response(color=$color, criteria=$criteria, name=$name, id=$id, tag=$tag)"
    }
}
