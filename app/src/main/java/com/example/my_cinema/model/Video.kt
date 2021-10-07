package com.example.my_cinema.model

data class Video(
    val duration: String,
    val error: Int,
    val height: Int,
    val host: String,
    val id: String,
    val original_ext: String,
    val preview: Int,
    val progress: Any,
    val ready: Int,
    val ready_full: Int,
    val real_type: String,
    val status: Int,
    val width: Int
)