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
import com.google.android.material.snackbar.Snackbar
import com.ynov.kotlin.rickmorty.R
import com.ynov.kotlin.rickmorty.presentation.list.adapter.EpisodeListAdapter
import com.ynov.kotlin.rickmorty.presentation.list.view.viewmodel.EpisodeListViewModel
import kotlinx.android.synthetic.main.fragment_list.*
import com.ynov.kotlin.rickmorty.presentation.list.view.listener.EndlessRecyclerViewScrollListener
import androidx.recyclerview.widget.RecyclerView




class EpisodeListFragment: Fragment() {

    private lateinit var episodeListAdapter: EpisodeListAdapter
    private lateinit var viewModel: EpisodeListViewModel
    private lateinit var scrollListener: EndlessRecyclerViewScrollListener

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        episodeListAdapter = EpisodeListAdapter()
        episodeListAdapter.onItemClick = {
            //startActivity(context?.let { context -> DetailActivity.newIntent(context, it.id) })
        }

        val linearLayoutManager = LinearLayoutManager(context)
        fragment_list_recyclerview.layoutManager = linearLayoutManager
        fragment_list_recyclerview.adapter = episodeListAdapter

        viewModel = ViewModelProviders.of(this).get(EpisodeListViewModel::class.java)
        viewModel.errorLiveData.observe(this, Observer {
            Snackbar.make(view, it.message.toString(), Snackbar.LENGTH_LONG).show()
        })
        viewModel.episodeListLiveData.observe(this, Observer {
            episodeListAdapter.updateList(it)
        })

        scrollListener = EndlessRecyclerViewScrollListener(linearLayoutManager)
        scrollListener.onLoadMore = { page: Int, _: Int, _: RecyclerView ->
            Log.d("RMInfiniteScroll", "EPISODES NEXT PAGE $page")
            viewModel.retrieveEpisodes(page)
        }

        fragment_list_recyclerview.addOnScrollListener(scrollListener)
    }
}