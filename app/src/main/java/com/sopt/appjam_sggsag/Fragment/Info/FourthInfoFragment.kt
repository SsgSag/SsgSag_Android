package com.sopt.appjam_sggsag.Fragment.Info

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.sopt.appjam_sggsag.R
import kotlinx.android.synthetic.main.activity_info.*

class FourthInfoFragment: Fragment(){
    private var fourthInfoFragment: View? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        fourthInfoFragment = inflater!!.inflate(R.layout.fragment_fourth_info, container, false)

        progress_1.setImageResource(R.drawable.progress_unactive)
        progress_2.setImageResource(R.drawable.progress_unactive)
        progress_3.setImageResource(R.drawable.progress_unactive)
        progress_4.setImageResource(R.drawable.progress_active)
        progress_5.setImageResource(R.drawable.progress_unactive)

        return fourthInfoFragment
    }

}