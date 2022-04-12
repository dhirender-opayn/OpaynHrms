package com.example.opaynhrms.model

data class LeaveCategoryJson(
    val `data`: List<Data>,
    val message: String
) {
    data class Data(
        val category: String,
        val created_at: String,
        val id: Int,
        val updated_at: String,
        var count:  String = "" , // self define

    )
}