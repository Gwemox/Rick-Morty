package com.ynov.kotlin.rickmorty.data.entity.remote

import com.google.gson.annotations.SerializedName

data class CharacterList (
    val info: Info,
    @SerializedName("results")
    val characterList: List<Character>
)