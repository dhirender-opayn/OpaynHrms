package com.example.opaynhrms.model

data class CommonModelCategoryList(
    val name: String,
    val id: Int,
    var total_leave: String? = "",
    var count: String? = "",
    var flag: Boolean = false
)