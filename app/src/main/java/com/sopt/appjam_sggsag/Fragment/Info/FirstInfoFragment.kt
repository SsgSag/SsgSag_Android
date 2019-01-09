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

class FirstInfoFragment: Fragment(){

    private var firstInfoFragment: View? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        firstInfoFragment = inflater!!.inflate(R.layout.fragment_first_info, container, false)

        return firstInfoFragment


    }

}