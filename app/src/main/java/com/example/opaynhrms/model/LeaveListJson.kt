package com.example.opaynhrms.model

import java.io.Serializable


data class LeaveListJson(
    val `data`: List<Data>,
    val message: String
) {

    data class Data(
        val created_at: String,
        val end_date: String,
        val `file`: String,
        val half: String,
        val id: Int,
        val leave_type: Int,
        val reason: String,
        val start_date: String,
        val status: Int,
        val leave_category_id : Int,
        val updated_at: String,
        val user: User,
        val user_id: Int
    ) :Serializable {
        data class User(
            val created_at: String,
            val email: String,
            val email_verified_at: String,
            val id: Int,
            val mobile: String,
            val name: String,
            val profile: Profile,
            val roles: List<Role>,
            val updated_at: String
        ) :Serializable {
            data class Profile(
                val clockify_key: String,
                val clockify_user_id: String,
                val clockify_workspace_id: String,
                val created_at: String,
                val id: Int,
                val image: String,
                val updated_at: String,
                val user_id: Int
            ):Serializable

            data class Role(
                val created_at: String,
                val guard_name: String,
                val id: Int,
                val name: String,
                val pivot: Pivot,
                val updated_at: String
            ) :Serializable {
                data class Pivot(
                    val model_id: Int,
                    val model_type: String,
                    val role_id: Int
                ):Serializable
            }
        }
    }
}