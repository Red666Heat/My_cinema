package com.example.my_cinema.model

data class ContentX(
    val color: String,
    val content: List<ContentXX>,
    val description: Any,
    val id: Int,
    val is_portrait: Boolean,
    val position: Int,
    val title: String
)