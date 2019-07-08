package com.ntxdroid.spacex.feature.launch

import android.graphics.Color
import android.graphics.PorterDuff
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.ntxdroid.spacex.R
import com.ntxdroid.spacex.core.extension.colorList
import com.ntxdroid.spacex.core.extension.repeatUntilSize
import com.ntxdroid.spacex.core.platform.AbstractListRecycler
import kotlinx.android.synthetic.main.launch_details_fragment.view.*
import kotlinx.android.synthetic.main.mission_card.view.*
import kotlinx.android.synthetic.main.mission_card.view.missionName

/**
 * Created by Al Warren on 1/31/2019.
 */

class LaunchesAdapter : AbstractListRecycler<LaunchView>() {
    override fun layoutId() = R.layout.launch_card

    override fun createViewHolder(view: View)  = LaunchViewHolder(view, collection.size)

    class LaunchViewHolder(itemView: View, size: Int): RecyclerView.ViewHolder(itemView), Binder<LaunchView> {
        private val colors = itemView.colorList().repeatUntilSize(size)
        override fun bind(data: LaunchView, clickListener: (LaunchView) -> Unit) {
            itemView.missionInitial.apply {
                text = data.missionName[0].toString()
                background.setColorFilter(Color.parseColor(colors[layoutPosition]),
                    PorterDuff.Mode.SRC_OVER)
            }
            itemView.missionName.text = data.missionName
            itemView.missionInitial.text = data.missionName[0].toString()
            itemView.launchDate.text = data.launchDate
            itemView.setOnClickListener { clickListener(data) }
        }
    }
}