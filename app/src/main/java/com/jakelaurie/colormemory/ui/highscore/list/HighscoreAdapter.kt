package com.jakelaurie.colormemory.ui.highscore.list

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.jakelaurie.colormemory.R
import com.jakelaurie.colormemory.model.Score
import javax.inject.Inject

class HighscoreAdapter @Inject constructor(): RecyclerView.Adapter<HighscoreViewHolder>() {
    init {
        setHasStableIds(true)
    }

    var data: List<Score> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HighscoreViewHolder {
        return HighscoreViewHolder(LayoutInflater.from(parent.context)
                .inflate(R.layout.item_high_score, parent, false)
        )
    }

    override fun onBindViewHolder(holder: HighscoreViewHolder, position: Int) {
        holder.bind(data[position], position)
    }

    override fun getItemId(position: Int): Long = data[position].id.hashCode().toLong()

    override fun getItemCount(): Int = data.size
}