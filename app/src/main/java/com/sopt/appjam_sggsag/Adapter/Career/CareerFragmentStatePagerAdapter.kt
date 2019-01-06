package com.sopt.appjam_sggsag.Adapter.Career

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import com.sopt.appjam_sggsag.Fragment.Career.Career1Fragment
import com.sopt.appjam_sggsag.Fragment.Career.Career2Fragment
import com.sopt.appjam_sggsag.Fragment.Career.Career3Fragment

class CareerFragmentStatePagerAdapter(fm: FragmentManager, val fCount: Int) : FragmentStatePagerAdapter(fm){
    override fun getItem(position: Int): Fragment? {
        when(position){
            0 -> return Career1Fragment()
            1 -> return Career2Fragment()
            2 -> return Career3Fragment()
            else -> return null
        }
    }

    override fun getCount(): Int = fCount
}