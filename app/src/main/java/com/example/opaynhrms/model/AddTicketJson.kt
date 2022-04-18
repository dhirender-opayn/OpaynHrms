package com.example.opaynhrms.model

data class AddTicketJson(
    val `data`: Data,
    val message: String
) {
    data class Data(
        val admin: Admin,
        val ticket: Ticket
    ) {
        data class Admin(
            val created_at: String,
            val email: String,
            val email_verified_at: Any,
            val id: Int,
            val mobile: String,
            val name: String,
            val profile: Profile,
            val updated_at: String
        ) {
            data class Profile(
                val clockify_key: String,
                val clockify_user_id: Any,
                val clockify_workspace_id: Any,
                val created_at: String,
                val id: Int,
                val image: String,
                val updated_at: String,
                val user_id: Int
            )
        }

        data class Ticket(
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
}