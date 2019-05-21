package com.ynov.kotlin.rickmorty.presentation.list.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.ynov.kotlin.rickmorty.R
import com.ynov.kotlin.rickmorty.presentation.list.adapter.CharacterListAdapter
import com.ynov.kotlin.rickmorty.presentation.list.view.viewmodel.CharacterListViewModel
import kotlinx.android.synthetic.main.fragment_list.*

class ListFragment: Fragment() {

    private lateinit var characterListAdapter: CharacterListAdapter
    private lateinit var viewModel: CharacterListViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        characterListAdapter = CharacterListAdapter()
        fragment_list_recyclerview.layoutManager = LinearLayoutManager(context)
        fragment_list_recyclerview.adapter = characterListAdapter
        //characterListAdapter.updateList()

        viewModel = ViewModelProviders.of(this).get(CharacterListViewModel::class.java)
        viewModel.characterListLiveData.observe(this, Observer {
            characterListAdapter.updateList(it)
        })
    }
}