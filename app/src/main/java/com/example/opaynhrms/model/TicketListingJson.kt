package com.example.opaynhrms.model

data class TicketListingJson(
    val `data`: List<Data>,
    val message: String
) {
    data class Data(
        val created_at: String,
        val deleted_at: Any,
        val description: String,
        val email: String,
        val id: Int,
        val mobile: String,
        val name: String,
        val status: Int,
        val subject: String,
        val ticket_number: Int,
        val updated_at: String,
        val user_id: Int
    )
}