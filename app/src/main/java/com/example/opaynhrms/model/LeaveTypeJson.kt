package com.example.opaynhrms.model

data class LeaveTypeJson(
    val `data`: List<Data>,
    val message: String
) {
    data class Data(
        val created_at: String,
        val id: Int,
        val type: String,
        val updated_at: String
    )
}