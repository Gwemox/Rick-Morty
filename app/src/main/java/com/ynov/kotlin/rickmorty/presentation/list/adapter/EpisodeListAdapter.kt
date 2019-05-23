package com.ynov.kotlin.rickmorty.presentation.list.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ynov.kotlin.rickmorty.R
import com.ynov.kotlin.rickmorty.data.entity.model.Episode
import kotlinx.android.synthetic.main.view_episode_list_item.view.*

class EpisodeListAdapter: RecyclerView.Adapter<EpisodeListAdapter.ViewHolder>() {
    private var episodeList: MutableList<Episode> = mutableListOf()
    var onItemClick: ((Episode) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater
            .from(parent.context)
            .inflate(R.layout.view_episode_list_item, parent, false))
    }

    override fun getItemCount(): Int = episodeList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(episodeList[position])
    }

    fun updateList(episodeList: List<Episode>) {
        //this.episodeList.clear()
        this.episodeList.addAll(episodeList)
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)  {
        init {
            itemView.setOnClickListener {
                this@EpisodeListAdapter.onItemClick?.invoke(episodeList[adapterPosition])
            }
        }

        fun bind(episode: Episode) {
            itemView.view_episode_item_name.text = episode.name
            itemView.view_episode_item_season.text = episode.episode
        }

    }
}