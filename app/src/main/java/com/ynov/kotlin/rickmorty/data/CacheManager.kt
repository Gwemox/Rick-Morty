package com.ynov.kotlin.rickmorty.data

import com.ynov.kotlin.rickmorty.data.entity.remote.Character
import com.ynov.kotlin.rickmorty.data.entity.remote.Episode
import io.reactivex.Single

class CacheManager: IManager {

    var characterListCache: HashMap<Int, List<Character>> = hashMapOf()
    var characterCache: HashMap<Int, Character> = hashMapOf()

    var episodeListCache: HashMap<Int, List<Episode>> = hashMapOf()
    var episodeCache: HashMap<Int, Episode> = hashMapOf()

    override fun retrieveCharacters(page: Int): Single<List<Character>> = Single.just(characterListCache[page])
    fun hasCacheRetrieveCharacters(page: Int): Boolean = characterListCache.containsKey(page)

    override fun retrieveCharacter(id: Int): Single<Character> = Single.just(this.characterCache.getValue(id))
    fun hasCacheRetrieveCharacter(id: Int): Boolean = this.characterCache.containsKey(id)


    override fun retrieveEpisodes(page: Int): Single<List<Episode>> = Single.just(episodeListCache[page])
    fun hasCacheRetrieveEpisodes(page: Int): Boolean = episodeListCache.containsKey(page)

    override fun retrieveEpisode(id: Int): Single<Episode> = Single.just(this.episodeCache.getValue(id))
    fun hasCacheRetrieveEpisode(id: Int): Boolean = this.episodeCache.containsKey(id)
}