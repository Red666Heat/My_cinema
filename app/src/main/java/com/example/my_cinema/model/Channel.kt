package com.example.my_cinema.model

data class Channel(
    val cover: CoverX,
    val created_at: String,
    val hours: List<Hour>,
    val id: Int,
    val languages: List<Language>,
    val logo: Logo,
    val promo: Promo,
    val stream: Any,
    val title: String,
    val updated_at: String,
    val viewer_profile: List<ViewerProfile>
)