package com.ynov.kotlin.rickmorty.data.entity.remote

import com.ynov.kotlin.rickmorty.data.entity.model.Character


class Character (
    val id: Int,
    val name: String,
    val status: Status,
    val species: Species,
    val type: String,
    val gender: Gender,
    val origin: Location,
    val location: Location,
    val image: String,
    val episode: List<String>,
    val url: String,
    val created: String
) {
    fun toModel(): Character = Character(id, name, image)
}