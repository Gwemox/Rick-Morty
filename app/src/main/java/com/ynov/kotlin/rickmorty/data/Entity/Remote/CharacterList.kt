package com.ynov.kotlin.rickmorty.data.Entity.Remote

import com.google.gson.annotations.SerializedName

data class CharacterList (
    val info: Info,
    @SerializedName("results")
    val characterList: List<Character>
)