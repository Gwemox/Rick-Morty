package com.ynov.kotlin.rickmorty.presentation

import android.app.Application
import com.ynov.kotlin.rickmorty.data.ApiManager
import com.ynov.kotlin.rickmorty.data.CacheManager
import com.ynov.kotlin.rickmorty.data.DataRepository

class RMApplication: Application() {

    lateinit var dataRepository: DataRepository

    companion object {
        lateinit var app: RMApplication
    }

    override fun onCreate() {
        super.onCreate()
        app = this

        initInjection()
    }

    private fun initInjection() {
        val apiManager = ApiManager()
        val cacheManager = CacheManager()
        dataRepository = DataRepository(apiManager, cacheManager)
    }
}