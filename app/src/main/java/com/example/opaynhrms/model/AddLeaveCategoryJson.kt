package com.example.opaynhrms.model

data class AddLeaveCategoryJson(
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
            val `data`: Data,
            val message: String
        ) {
            data class Data(
                val available_leaves: String,
                val created_at: String,
                val id: Int,
                val leave_category_id: String,
                val total_leaves: String,
                val updated_at: String,
                val user_id: String
            )
        }
    }
}