package com.sopt.appjam_sggsag.Fragment.Info

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.sopt.appjam_sggsag.R
class SecondInfoFragment: Fragment(){

    private var secondInfoFragment: View? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        secondInfoFragment = inflater!!.inflate(R.layout.fragment_second_info, container, false)



        return secondInfoFragment
    }

}