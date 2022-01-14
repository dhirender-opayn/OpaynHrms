package com.example.opaynhrms.model

data class ClockifyListJson(
    val `data`: List<Data>,
    val message: String
) {
    data class Data(
        val billable: Boolean,
        val customFieldValues: List<Any>,
        val description: String,
        val id: String,
        val isLocked: Boolean,
        val projectId: Any,
        val tagIds: Any,
        val taskId: Any,
        val timeInterval: TimeInterval,
        val userId: String,
        val workspaceId: String
    ) {
        data class TimeInterval(
            val duration: Any,
            val end: String,
            val start: String
        )
    }
}