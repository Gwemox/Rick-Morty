package com.ynov.kotlin.rickmorty.data.entity.remote

import com.google.gson.annotations.SerializedName

data class EpisodeList (
    val info: Info,
    @SerializedName("results")
    val episodeList: List<Episode>
)