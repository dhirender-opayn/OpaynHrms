package com.example.opaynhrms.model

data class UserLeaveDetailJson(
    val `data`: List<Data>,
    val message: String
) {
    data class Data(
        val available_leaves: Int,
        val leave_category: Catgeory,
        val created_at: String,
        val id: Int,
        val leave_Category_id: Int,
        val leave_category_id: Int,
        val total_leaves: Int,
        val updated_at: String,
        val user_id: Int
    ) {
        data class Catgeory(
            val category: String,
            val created_at: String,
            val id: Int,
            val updated_at: String
        )
    }
}