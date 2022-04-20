package com.example.opaynhrms.model

data class HolidayListingJson(
    val `data`: List<Data>,
    val message: String
) {
    data class Data(
        val created_at: String,
        val date: String,
        val description: String,
        val id: Int,
        val title: String,
        val updated_at: String
    )
}