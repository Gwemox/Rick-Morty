package com.ynov.kotlin.rickmorty.data.entity.remote

import com.ynov.kotlin.rickmorty.data.entity.model.Episode

class Episode (
    val id: Int,
    val name: String,
    val airDate: String,
    val episode: String,
    val characters: List<String>,
    val url: String,
    val created: String
) {
    fun toModel(): Episode = Episode(id, name, episode)
}