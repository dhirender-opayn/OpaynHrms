package com.example.opaynhrms.model

data class UserDetailJson(
    val `data`: Data,
    val message: String
) {
    data class Data(
        val attandances: List<Attandance>,
        val created_at: String,
        val email: String,
        val email_verified_at: Any,
        val id: Int,
        val leaves: List<Leaves>,
        val mobile: String,
        val name: String,
        val profile: Profile,
        val roles: List<Role>,
        val updated_at: String
    ) {
        data class Attandance(
            val created_at: String,
            val id: Int,
            val lat: String,
            val lng: String,
            val timing: String,
            val type: String,
            val updated_at: String,
            val user_id: Int
        )

        data class Leaves(
            val created_at: String,
            val id: Int,
            val user_id: String,
            val start_date: String,
            val end_date: String,
            val leave_type: String,
            val status: String,
            val file: String,
            val reason: String
        )

        data class Profile(
            val id: Int,
            val image: Any,
            val user_id: Int
        )

        data class Role(
            val id: Int,
            val name: String,
            val pivot: Pivot
        ) {
            data class Pivot(
                val model_id: Int,
                val model_type: String,
                val role_id: Int
            )
        }
    }
}