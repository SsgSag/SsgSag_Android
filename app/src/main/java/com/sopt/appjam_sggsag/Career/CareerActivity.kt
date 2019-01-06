package com.sopt.appjam_sggsag.Career

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.RelativeLayout
import com.sopt.appjam_sggsag.Adapter.Career.CareerFragmentStatePagerAdapter
import com.sopt.appjam_sggsag.R
import kotlinx.android.synthetic.main.activity_career.*

class CareerActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_career)
        configureBottomNavigation()
        setBtnOnClickListener()
    }

    private fun configureBottomNavigation(){
        vp_career_act_view_frag_pager.adapter = CareerFragmentStatePagerAdapter(supportFragmentManager, 3)
        vp_career_act_view_frag_pager.offscreenPageLimit = 3

        tl_top_navi_career_top_menu.setupWithViewPager(vp_career_act_view_frag_pager)

        val TopNaviLayout : View = this.layoutInflater.inflate(R.layout.top_navi_career, null, false)

        tl_top_navi_career_top_menu.getTabAt(0)!!.customView = TopNaviLayout.findViewById(R.id.btn_top_navi_activity_tab) as RelativeLayout
        tl_top_navi_career_top_menu.getTabAt(1)!!.customView = TopNaviLayout.findViewById(R.id.btn_top_navi_award_tab) as RelativeLayout
        tl_top_navi_career_top_menu.getTabAt(2)!!.customView = TopNaviLayout.findViewById(R.id.btn_top_navi_certificate_tab) as RelativeLayout
    }

    private fun setBtnOnClickListener(){
        btn_iv_back_career.setOnClickListener {
            finish()
        }
    }
}