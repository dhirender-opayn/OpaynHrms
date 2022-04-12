package com.example.opaynhrms.model

data class CheckInCheckOutJson(
    val `data`: Data,
    val message: String
) {
    data class Data(
        val created_at: String,
        val id: Int,
        val lat: String,
        val lng: String,
        val timing: String,
        val type: String,
        val updated_at: String,
        val user_id: Int
    )
}