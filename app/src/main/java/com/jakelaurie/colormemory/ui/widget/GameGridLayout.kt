package com.jakelaurie.colormemory.ui.widget

import android.content.Context
import android.util.AttributeSet
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.widget.LinearLayout
import com.jakelaurie.colormemory.ui.game.GameViewAdapter
import com.jakelaurie.colormemory.util.closestDivisible

class GameGridLayout @JvmOverloads constructor(context: Context,
                                               attrs: AttributeSet? = null,
                                               defStyle: Int = 0,
                                               defStyleRes: Int = 0):
        LinearLayout(context, attrs, defStyle, defStyleRes) {
    init {
        orientation = VERTICAL
    }

    var adapter: GameViewAdapter? = null
        set(value) {
            field = value
            removeAllViews()
            addAllViews()
        }

    private fun addAllViews() {
        val rowParams = LayoutParams(MATCH_PARENT, 0)
        rowParams.weight = 1f

        val colParams = LayoutParams(0, MATCH_PARENT)
        colParams.weight = 1f

        adapter?.let {
            val size = it.getItemCount()
            val gridSize = size.closestDivisible(4) / 4 //w=h
            weightSum = gridSize.toFloat()

            var currentColumn = 0
            var currentLayout = buildRow(gridSize, rowParams)
            for(i in 0..(size -1)) {
                if(currentColumn == gridSize) {
                    currentColumn = 0
                    currentLayout = buildRow(gridSize, rowParams)
                }

                val view = it.getView(i, this)
                currentLayout.addView(view, colParams)
                currentColumn ++
            }
        }
    }

    private fun buildRow(columnCount: Int, rowParams: LayoutParams): LinearLayout {
        val layout = LinearLayout(this.context)
        layout.orientation = HORIZONTAL
        layout.weightSum = columnCount.toFloat()
        addView(layout, rowParams)
        return layout
    }
}