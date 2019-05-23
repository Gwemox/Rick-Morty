package com.ynov.kotlin.rickmorty.data.entity.remote

data class Info (
    val count: Int,
    val pages: Int,
    val next: String,
    val prev: String
)