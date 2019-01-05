package com.sopt.appjam_sggsag.Fragment.Info

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.sopt.appjam_sggsag.R
class ThirdInfoFragment: Fragment(){

    private var thirdInfoFragment: View? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        thirdInfoFragment = inflater!!.inflate(R.layout.fragment_third_info, container, false)


        return thirdInfoFragment
    }

}