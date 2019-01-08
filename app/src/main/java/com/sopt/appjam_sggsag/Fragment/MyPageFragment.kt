package com.sopt.appjam_sggsag.Fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import com.sopt.appjam_sggsag.Career.CareerActivity
import com.sopt.appjam_sggsag.MyPage.InterestArea
import com.sopt.appjam_sggsag.MyPage.JobActivity
import com.sopt.appjam_sggsag.R
import org.jetbrains.anko.find
import org.jetbrains.anko.support.v4.startActivity
import org.jetbrains.anko.support.v4.toast

class MyPageFragment:Fragment(){
    private var myPageFragment: View? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        myPageFragment = inflater!!.inflate(R.layout.fragment_my_page, container, false)

        setOnBtnClickListener()

        return myPageFragment
    }

    companion object {
        private var instance : MyPageFragment? = null
        @Synchronized
        fun getInstance() : MyPageFragment {
            if (instance == null){
                instance = MyPageFragment()
            }
            return instance!!
        }
    }

    private fun setOnBtnClickListener(){
        val btn_profile_setting: RelativeLayout = myPageFragment!!.find(R.id.btn_my_page_profile_setting)
        btn_profile_setting.setOnClickListener {
            toast("프로필 변경")
        }
        val btn_preference: TextView = myPageFragment!!.find(R.id.btn_my_page_preference)
        btn_preference.setOnClickListener {
            startActivity<InterestArea>()
        }
        val btn_job: TextView = myPageFragment!!.find(R.id.btn_my_page_job)
        btn_job.setOnClickListener {
            startActivity<JobActivity>()
        }
        val btn_career: TextView = myPageFragment!!.find(R.id.btn_my_page_career)
        btn_career.setOnClickListener {
            startActivity<CareerActivity>()
        }
        val btn_notice: RelativeLayout = myPageFragment!!.find(R.id.btn_my_page_notice)
        btn_notice.setOnClickListener {
            toast("공지사항")
        }
        val btn_push_setting: RelativeLayout = myPageFragment!!.find(R.id.btn_my_page_push_setting)
        btn_push_setting.setOnClickListener {
            toast("푸시 알림 설정")
        }
        val btn_account: RelativeLayout = myPageFragment!!.find(R.id.btn_my_page_account_management)
        btn_account.setOnClickListener {
            toast("계정관리")
        }
    }
}