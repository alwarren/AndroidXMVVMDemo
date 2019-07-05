package com.ntxdroid.spacex.feature.mission

import android.graphics.Color
import android.graphics.PorterDuff
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.ntxdroid.spacex.R
import com.ntxdroid.spacex.core.extension.repeatUntilSize
import com.ntxdroid.spacex.core.extension.colorList
import com.ntxdroid.spacex.core.platform.AbstractListRecycler
import kotlinx.android.synthetic.main.mission_card.view.*

/**
 * Created by Al Warren on 1/31/2019.
 */

class MissionsAdapter : AbstractListRecycler<MissionView>() {
    override fun layoutId() = R.layout.mission_card

    override fun createViewHolder(view: View)  = MissionViewHolder(view, collection.size)

    class MissionViewHolder(itemView: View, size: Int): RecyclerView.ViewHolder(itemView), Binder<MissionView> {
        private val colors = itemView.colorList().repeatUntilSize(size)
        override fun bind(data: MissionView, clickListener: (MissionView) -> Unit) {
            itemView.missionInitial.apply {
                text = data.missionName[0].toString()
                background.setColorFilter(Color.parseColor(colors[layoutPosition]),
                    PorterDuff.Mode.SRC_OVER)
            }
            itemView.missionName.text = data.missionName
            itemView.missionInitial.text = data.missionName[0].toString()
            itemView.manufacturers.text = data.manufacturers.joinToString(", ")
            itemView.setOnClickListener { clickListener(data) }
        }
    }
}