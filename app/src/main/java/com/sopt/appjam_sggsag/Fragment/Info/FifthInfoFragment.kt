package com.sopt.appjam_sggsag.Fragment.Info

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import com.sopt.appjam_sggsag.LoginActivity
import com.sopt.appjam_sggsag.R
import kotlinx.android.synthetic.main.activity_info.*
import org.jetbrains.anko.find
import org.jetbrains.anko.support.v4.startActivity

class FifthInfoFragment: Fragment(){
    private var fifthInfoFragment: View? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        fifthInfoFragment = inflater!!.inflate(R.layout.fragment_fifth_info, container, false)

        setOnBtnClickListener()

        progress_1.setImageResource(R.drawable.progress_unactive)
        progress_2.setImageResource(R.drawable.progress_unactive)
        progress_3.setImageResource(R.drawable.progress_unactive)
        progress_4.setImageResource(R.drawable.progress_unactive)
        progress_5.setImageResource(R.drawable.progress_active)

        return fifthInfoFragment
    }

    private fun setOnBtnClickListener(){
//        val fifth_skip: RelativeLayout = fifthInfoFragment!!.find(R.id.btn_fifth_skip)
//        fifth_skip.setOnClickListener {
//            startActivity<LoginActivity>()
//            activity!!.finish()
//        }
    }

}