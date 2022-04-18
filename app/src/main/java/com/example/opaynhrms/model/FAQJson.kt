package com.example.opaynhrms.model

data class FAQJson(
    val `data`: List<Data>,
    val message: String
) {
    data class Data(
        val answer: String,
        val created_at: String,
        val id: Int,
        val question: String,
        val updated_at: String
    )
}