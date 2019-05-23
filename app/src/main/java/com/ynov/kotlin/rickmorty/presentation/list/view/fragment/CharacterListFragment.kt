package com.ynov.kotlin.rickmorty.presentation.list.view.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ynov.kotlin.rickmorty.R
import com.ynov.kotlin.rickmorty.extension.showSnackbar
import com.ynov.kotlin.rickmorty.presentation.DetailActivity
import com.ynov.kotlin.rickmorty.presentation.list.adapter.CharacterListAdapter
import com.ynov.kotlin.rickmorty.presentation.list.view.listener.EndlessRecyclerViewScrollListener
import com.ynov.kotlin.rickmorty.presentation.list.view.viewmodel.CharacterListViewModel
import kotlinx.android.synthetic.main.fragment_list.*

class CharacterListFragment: Fragment() {

    private lateinit var characterListAdapter: CharacterListAdapter
    private lateinit var viewModel: CharacterListViewModel
    private lateinit var scrollListener: EndlessRecyclerViewScrollListener

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        characterListAdapter = CharacterListAdapter()
        characterListAdapter.onItemClick = {
            startActivity(context?.let { context -> DetailActivity.newIntent(context, it.id) })
        }

        val linearLayoutManager = LinearLayoutManager(context)
        fragment_list_recyclerview.layoutManager = linearLayoutManager
        fragment_list_recyclerview.adapter = characterListAdapter

        viewModel = ViewModelProviders.of(this).get(CharacterListViewModel::class.java)
        viewModel.errorLiveData.observe(this, Observer {
            view.showSnackbar(it.message.toString())
        })
        viewModel.characterListLiveData.observe(this, Observer {
            characterListAdapter.updateList(it)
        })
        scrollListener = EndlessRecyclerViewScrollListener(linearLayoutManager)
        scrollListener.onLoadMore = { page: Int, _: Int, _: RecyclerView ->
            Log.d("RMInfiniteScroll", "CHARACTERS NEXT PAGE $page")
            viewModel.retrieveCharacters(page)
        }

        fragment_list_recyclerview.addOnScrollListener(scrollListener)
    }
}