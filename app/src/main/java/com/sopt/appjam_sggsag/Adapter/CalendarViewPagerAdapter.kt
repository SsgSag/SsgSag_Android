package com.sopt.appjam_sggsag.Adapter

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import com.sopt.appjam_sggsag.Fragment.CalendarDetailFragment
import com.sopt.appjam_sggsag.Interface.GetYearMonthTab
import java.util.*

class CalendarViewPagerAdapter(fm: FragmentManager?, val fCount: Int, val listener:GetYearMonthTab) : FragmentStatePagerAdapter(fm) {

    internal var mCallback: GetYearMonthTab = listener

    var beforeMonth=24;
    var year = 0
    var toDay = 0;
    var nowMonth = 0;
    override fun getItem(position: Int): Fragment? {
        // Log.e("CalendarOrder",position.toString())
        val fragment = CalendarDetailFragment() // Fragment 생성
        val bundle = Bundle(1) // 파라미터는 전달할 데이터 개수
        bundle.putInt("diff", position-24) // key , value
        val iCal: Calendar = Calendar.getInstance()
        toDay = iCal.get(Calendar.MONTH)
        if(beforeMonth<position){
            iCal.add(Calendar.MONTH,toDay+position-24-1)
            beforeMonth = position
        }
        else if(beforeMonth>position){
            iCal.add(Calendar.MONTH,toDay+position-24+1)
            beforeMonth = position
        }
        else{
            iCal.add(Calendar.MONTH,toDay+position-24)
        }
        year = iCal.get(Calendar.YEAR)
        toDay = iCal.get(Calendar.MONTH)
        nowMonth = (toDay+1)
        mCallback.getYearMonthTab(year.toString(),nowMonth.toString())
        fragment.setArguments(bundle)
        //beforeMonth
        return fragment
    }

    override fun getCount(): Int = fCount //return fCount
}