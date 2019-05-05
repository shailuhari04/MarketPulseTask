package com.marketpulsetask.pojo

data class TestResponse(
    val color: String,
    val criteria: List<Criteria>,
    val id: Int,
    val name: String,
    val tag: String
)