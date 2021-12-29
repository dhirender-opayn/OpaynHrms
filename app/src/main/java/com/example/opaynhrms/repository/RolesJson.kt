package com.example.opaynhrms.repository

data class RolesJson(
    val `data`: List<Data>,
    val message: String
) {
    data class Data(
        val id: Int,
        val name: String
    )
}