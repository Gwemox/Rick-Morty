package com.ynov.kotlin.rickmorty.data
import com.ynov.kotlin.rickmorty.data.entity.model.Character as RMCharacter
import com.ynov.kotlin.rickmorty.data.entity.model.Episode as RMEpisode
import io.reactivex.Single
import com.ynov.kotlin.rickmorty.data.entity.remote.Character
import com.ynov.kotlin.rickmorty.data.entity.remote.Episode

class DataRepository(
    private val apiManager: ApiManager,
    private val cacheManager: CacheManager
) {
    fun retrieveCharacters(page: Int): Single<List<RMCharacter>> = Single.defer {
        val singleResult: Single<List<Character>> = if (cacheManager.hasCacheRetrieveCharacters(page)) {
            cacheManager.retrieveCharacters(page)
        } else {
            apiManager.retrieveCharacters(page).doAfterSuccess { cacheManager.characterListCache[page] = it }
        }
        singleResult.map {
            it.map { character -> character.toModel()}
        }
    }

    fun retrieveCharacter(id: Int): Single<RMCharacter> = Single.defer {
        val singleResult: Single<Character> = if (cacheManager.hasCacheRetrieveCharacter(id)) {
            cacheManager.retrieveCharacter(id)
        } else {
            apiManager.retrieveCharacter(id).doAfterSuccess { cacheManager.characterCache[id] = it }
        }
        singleResult.map { character -> character.toModel()}
    }

    fun retrieveEpisodes(page: Int): Single<List<RMEpisode>> = Single.defer {
        val singleResult: Single<List<Episode>> = if (cacheManager.hasCacheRetrieveEpisodes(page)) {
            cacheManager.retrieveEpisodes(page)
        } else {
            apiManager.retrieveEpisodes(page).doAfterSuccess { cacheManager.episodeListCache[page] = it }
        }
        singleResult.map {
            it.map { episode -> episode.toModel()}
        }
    }

    fun retrieveEpisode(id: Int): Single<RMEpisode> = Single.defer {
        val singleResult: Single<Episode> = if (cacheManager.hasCacheRetrieveEpisode(id)) {
            cacheManager.retrieveEpisode(id)
        } else {
            apiManager.retrieveEpisode(id).doAfterSuccess { cacheManager.episodeCache[id] = it }
        }
        singleResult.map { episode -> episode.toModel()}
    }
}