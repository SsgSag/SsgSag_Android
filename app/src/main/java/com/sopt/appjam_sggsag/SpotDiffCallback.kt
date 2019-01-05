package com.sopt.appjam_sggsag

import android.support.v7.util.DiffUtil
import com.sopt.appjam_sggsag.Data.PosterData


class SpotDiffCallback(
    private val old: List<PosterData>,
    private val new: List<PosterData>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int {
        return old.size
    }

    override fun getNewListSize(): Int {
        return new.size
    }

    override fun areItemsTheSame(oldPosition: Int, newPosition: Int): Boolean {
        return old[oldPosition].posterIdx == new[newPosition].posterIdx
    }

    override fun areContentsTheSame(oldPosition: Int, newPosition: Int): Boolean {
        return old[oldPosition] == new[newPosition]
    }

}
