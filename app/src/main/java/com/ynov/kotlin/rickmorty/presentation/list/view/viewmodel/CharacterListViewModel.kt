package com.ynov.kotlin.rickmorty.presentation.list.view.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ynov.kotlin.rickmorty.data.Entity.Model.Character
import com.ynov.kotlin.rickmorty.presentation.RMApplication
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

class CharacterListViewModel: ViewModel() {

    var characterListLiveData: MutableLiveData<List<Character>> = MutableLiveData()

    init {
       this.retrieveCharacters()
    }

    private fun retrieveCharacters() {
        val subscribeBy = RMApplication.app.dataRepository.retrieveCharacters()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onSuccess = {
                    characterListLiveData.postValue(it)
                },
                onError = {
                    Log.e("ERROR", "Can't receive data", it)
                }
            )
    }
}