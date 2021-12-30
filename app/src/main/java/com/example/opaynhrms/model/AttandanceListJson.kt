package com.example.opaynhrms.model

data class AttandanceListJson(
    val `data`: Data,
    val message: String
) {
    data class Data(
        val current_page: Int,
        val `data`: List<Data>,
        val first_page_url: String,
        val from: Int,
        val last_page: Int,
        val last_page_url: String,
        val links: List<Link>,
        val next_page_url: String,
        val path: String,
        val per_page: Int,
        val prev_page_url: String,
        val to: Int,
        val total: Int
    ) {
        data class Data(
            val created_at: String,
            val id: Int,
            val lat: String,
            val lng: String,
            val timing: String,
            val type: String,
            val updated_at: String,
            val user_id: Int
        )

        data class Link(
            val active: Boolean,
            val label: String,
            val url: String
        )
    }
}