package com.ynov.kotlin.rickmorty.presentation.list.view.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ynov.kotlin.rickmorty.data.entity.model.Character
import com.ynov.kotlin.rickmorty.presentation.RMApplication
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

class CharacterListViewModel: ViewModel() {

    var characterListLiveData: MutableLiveData<List<Character>> = MutableLiveData()
    var errorLiveData: MutableLiveData<Throwable> = MutableLiveData()
    private var compositeDisposable: CompositeDisposable = CompositeDisposable()

    init {
       this.retrieveCharacters(1)
    }

    override fun onCleared() = compositeDisposable.clear()

    fun retrieveCharacters(page: Int) {
        compositeDisposable.add(RMApplication.app.dataRepository.retrieveCharacters(page)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onSuccess = {
                    characterListLiveData.postValue(it)
                },
                onError = {
                    errorLiveData.postValue(it)
                    Log.e("ERROR", "Can't receive data", it)
                }
            )
        )
    }
}