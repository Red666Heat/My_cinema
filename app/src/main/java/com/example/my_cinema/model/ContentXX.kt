package com.example.my_cinema.model

data class ContentXX(
    val count_episodes: Int,
    val cover: CoverXX,
    val cover_recommended: Any,
    val created_at: String,
    val duration: String,
    val episodes: EpisodesX,
    val format: String,
    val genre: Genre,
    val genre_id: Int,
    val id: Int,
    val languages: List<LanguageX>,
    val title: String,
    val updated_at: String,
    val years_production: String
)