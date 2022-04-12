package com.example.opaynhrms.model

data class UserLeaveDetailJson(
    val `data`: Data,
    val message: String
) {
    data class Data(
        val exception: Any,
        val headers: Headers,
        val original: Original
    ) {
        class Headers

        data class Original(
            val `data`: List<Data>,
            val message: String
        ) {
            data class Data(
                val available_leaves: Int,
                val created_at: String,
                val id: Int,
                val leave_Category_id: Int,
                val total_leaves: Int,
                val updated_at: String,
                val user_id: Int
            )
        }
    }
}