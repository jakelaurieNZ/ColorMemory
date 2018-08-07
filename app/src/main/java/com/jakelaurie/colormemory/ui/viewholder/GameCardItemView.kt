package com.jakelaurie.colormemory.ui.viewholder

import android.animation.ObjectAnimator
import android.graphics.drawable.Drawable
import android.view.View
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.SimpleTarget
import com.bumptech.glide.request.transition.Transition
import com.jakelaurie.colormemory.domain.model.GameCard
import kotlinx.android.synthetic.main.item_game_card.view.*
import android.view.animation.AccelerateDecelerateInterpolator
import com.jakelaurie.colormemory.util.addEndListener

typealias ClickListener = ((View, Int) -> Unit)

class GameCardItemViewHolder(val itemView: View) {
    private var isSelected = false
    private var currentId = -1
    private var actionBlocked = false

    init {
        itemView.id = View.generateViewId()
    }

    fun bind(item: GameCard, position: Int, clickListener: ClickListener?) {
        currentId = item.pairId
        itemView.tag = this

        val animate = isSelected != item.selected

        Glide.with(itemView)
                .load(if(item.selected) item.revealedDrawableRes else item.defaultDrawableRes)
                .into(object: SimpleTarget<Drawable>() {
                    override fun onResourceReady(resource: Drawable, transition: Transition<in Drawable>?) {
                        //Animate rotationY and set new image rather than more bitmaps in mem
                        if(animate) {
                            val animation = ObjectAnimator.ofFloat(
                                    itemView,
                                    "rotationY",
                                    if(item.selected) 0.0f else 180f,
                                    if(item.selected) 180f else 0.0f
                            )

                            var hasChanged = false
                            animation.duration = 700
                            animation.interpolator = AccelerateDecelerateInterpolator()

                            animation.addUpdateListener {
                                if(animation.animatedFraction >= 0.5 && !hasChanged) {
                                    hasChanged = true
                                    itemView.gameCardImageView.setImageDrawable(resource)
                                    //Flip imageview
                                    itemView.gameCardImageView.scaleX = if(item.selected) -1f else 1f
                                }
                            }

                            animation.addEndListener {
                                actionBlocked = false
                            }

                            actionBlocked = true
                            animation.start()
                        } else {
                            itemView.gameCardImageView.setImageDrawable(resource)
                        }

                    }
                })

        itemView.setOnClickListener {
            if(!actionBlocked) {
                clickListener?.invoke(itemView, position)
            }
        }

        isSelected = item.selected
    }
}