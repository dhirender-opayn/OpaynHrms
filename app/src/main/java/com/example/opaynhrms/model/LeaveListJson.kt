package com.example.opaynhrms.model

data class LeaveListJson(
    val `data`: List<Data>,
    val message: String
) {
    data class Data(
        val created_at: String,
        val end_date: String,
        val `file`: Any,
        val half: Any,
        val id: Int,
        val leave_type: Int,
        val reason: String,
        val start_date: String,
        val status: Int,
        val updated_at: String,
        val user: User,
        val user_id: Int
    ) {
        data class User(
            val created_at: String,
            val email: String,
            val email_verified_at: Any,
            val id: Int,
            val mobile: String,
            val name: String,
            val profile: Profile,
            val roles: List<Role>,
            val updated_at: String
        ) {
            data class Profile(
                val clockify_key: String,
                val clockify_user_id: String,
                val clockify_workspace_id: String,
                val created_at: String,
                val id: Int,
                val image: String,
                val updated_at: String,
                val user_id: Int
            )

            data class Role(
                val created_at: String,
                val guard_name: String,
                val id: Int,
                val name: String,
                val pivot: Pivot,
                val updated_at: String
            ) {
                data class Pivot(
                    val model_id: Int,
                    val model_type: String,
                    val role_id: Int
                )
            }
        }
    }
}