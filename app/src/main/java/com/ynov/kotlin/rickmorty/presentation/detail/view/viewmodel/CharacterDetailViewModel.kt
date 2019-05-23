package com.ynov.kotlin.rickmorty.presentation.detail.view.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ynov.kotlin.rickmorty.data.entity.model.Character
import com.ynov.kotlin.rickmorty.presentation.RMApplication
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

class CharacterDetailViewModel: ViewModel() {

    var characterLiveData: MutableLiveData<Character> = MutableLiveData()
    var errorLiveData: MutableLiveData<Throwable> = MutableLiveData()


    fun retrieveCharacter(characterId: Int) {
        val subscribeBy = RMApplication.app.dataRepository.retrieveCharacter(characterId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onSuccess = {
                    characterLiveData.postValue(it)
                },
                onError = {
                    errorLiveData.postValue(it)
                    Log.e("ERROR", "Can't receive data", it)
                }
            )
    }
}