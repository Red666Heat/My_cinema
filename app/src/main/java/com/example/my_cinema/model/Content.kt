package com.example.my_cinema.model

data class Content(
    val count_episodes: Int,
    val cover: Cover,
    val cover_recommended: CoverRecommended,
    val created_at: String,
    val duration: String,
    val episodes: Episodes,
    val format: String,
    val genre_id: Int,
    val id: Int,
    val title: String,
    val updated_at: String,
    val years_production: String
)