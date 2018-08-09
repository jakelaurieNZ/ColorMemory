package com.jakelaurie.colormemory.ui.highscore.list

import android.support.v7.widget.RecyclerView
import android.view.View
import com.jakelaurie.colormemory.model.Score
import kotlinx.android.synthetic.main.item_high_score.view.*
import java.util.*

class HighscoreViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    fun bind(item: Score, position: Int) {
        itemView.itemHighScoreRank.text = String.format(Locale.getDefault(), "#%d", position + 1)
        itemView.itemHighScoreName.text = item.playerName
        itemView.itemHighScoreScore.text = String.format(Locale.getDefault(), "%d", item.score)
    }
}