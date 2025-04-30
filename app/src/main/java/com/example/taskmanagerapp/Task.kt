package com.yourdomain.tasklist.data.model

import com.google.firebase.firestore.DocumentId

data class Task(
    @DocumentId
    val id: String = "",
    val title: String = "",
    val description: String = "",
    var isCompleted: Boolean = false,
    val userId: String = "",
    val createdAt: Long = System.currentTimeMillis()
)
