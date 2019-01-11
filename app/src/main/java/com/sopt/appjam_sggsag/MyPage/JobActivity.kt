package com.sopt.appjam_sggsag.MyPage

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.sopt.appjam_sggsag.MyApplication
import com.sopt.appjam_sggsag.R
import kotlinx.android.synthetic.main.activity_job.*
import org.jetbrains.anko.toast

class JobActivity : AppCompatActivity() {

    lateinit var interestListServer: Array<Byte>
    //    var interestListServer: ArrayList<Byte> = ArrayList()
    var interestList: ArrayList<Int> = arrayListOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)
    var check = 0
    lateinit var gender: String

    val networkService: NetworkService by lazy {
        MyApplication.instance.networkService
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_job)

        btn_iv_back_job.setOnClickListener {
            finish()
        }

        if(sw_job.isChecked)
            setOnBtnClickListener()

        sw_job.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked)
                setOnBtnClickListener()
            else
                clearBtnClicked()
        }

    }

    private fun clearBtnClicked(){
        btn_job_management.setImageResource(R.drawable.bt_job_management_unactive)
        btn_job_marketing.setImageResource(R.drawable.bt_job_marketing_unactive)
        btn_job_tech.setImageResource(R.drawable.bt_job_tech_unactive)
        btn_job_design.setImageResource(R.drawable.bt_job_design_unactive)
        btn_job_trade.setImageResource(R.drawable.bt_job_trade_unactive)
        btn_job_sales.setImageResource(R.drawable.bt_job_sales_unactive)
        btn_job_service.setImageResource(R.drawable.bt_job_service_unactive)
        btn_job_study.setImageResource(R.drawable.bt_job_study_unactive)
        btn_job_industry.setImageResource(R.drawable.bt_job_industry_unactive)
        btn_job_edu.setImageResource(R.drawable.bt_job_literature_unactive)
        btn_job_construct.setImageResource(R.drawable.bt_job_construct_unactive)
        btn_job_media.setImageResource(R.drawable.bt_job_medical_unactive)
        btn_job_media.setImageResource(R.drawable.bt_job_art_unactive)
        btn_job_media.setImageResource(R.drawable.bt_job_speciality_unactive)

        for(i in 1..0)
            interestList[i]=0
        check=0
        btn_job_save.setImageResource(R.drawable.bt_save_mypage_unactive)
    }

    private fun setOnBtnClickListener() {

        // 기획/아이디어
        btn_job_management.setOnClickListener {
            if (interestList[0] == 0) {
                interestList[0] = 1
                btn_job_management.setImageResource(R.drawable.bt_job_management_active)
            } else {
                interestList[0] = 0
                btn_job_management.setImageResource(R.drawable.bt_job_management_unactive)
            }
            changInput()
        }

        btn_job_marketing.setOnClickListener {
            if (interestList[1] == 0) {
                interestList[1] = 1
                btn_job_marketing.setImageResource(R.drawable.bt_job_marketing_active)
            } else {
                interestList[1] = 0
                btn_job_marketing.setImageResource(R.drawable.bt_job_marketing_unactive)
            }
            changInput()
        }

        btn_job_tech.setOnClickListener {
            if (interestList[2] == 0) {
                interestList[2] = 1
                btn_job_tech.setImageResource(R.drawable.bt_job_tech_active)
            } else {
                interestList[2] = 0
                btn_job_tech.setImageResource(R.drawable.bt_job_tech_unactive)
            }
            changInput()
        }

        btn_job_design.setOnClickListener {
            if (interestList[3] == 0) {
                interestList[3] = 1
                btn_job_design.setImageResource(R.drawable.bt_job_design_active)
            } else {
                interestList[3] = 0
                btn_job_design.setImageResource(R.drawable.bt_job_design_unactive)
            }
            changInput()
        }
        btn_job_trade.setOnClickListener {
            if (interestList[4] == 0) {
                interestList[4] = 1
                btn_job_trade.setImageResource(R.drawable.bt_job_trade_active)
            } else {
                interestList[4] = 0
                btn_job_trade.setImageResource(R.drawable.bt_job_trade_unactive)
            }
            changInput()
        }
        btn_job_sales.setOnClickListener {
            if (interestList[5] == 0) {
                interestList[5] = 1
                btn_job_sales.setImageResource(R.drawable.bt_job_sales_active)
            } else {
                interestList[5] = 0
                btn_job_sales.setImageResource(R.drawable.bt_job_sales_unactive)
            }
            changInput()
        }
        btn_job_service.setOnClickListener {
            if (interestList[6] == 0) {
                interestList[6] = 1
                btn_job_service.setImageResource(R.drawable.bt_job_service_active)
            } else {
                interestList[6] = 0
                btn_job_service.setImageResource(R.drawable.bt_job_service_unactive)
            }
            changInput()
        }
        btn_job_study.setOnClickListener {
            if (interestList[7] == 0) {
                interestList[7] = 1
                btn_job_study.setImageResource(R.drawable.bt_job_study_active)
            } else {
                interestList[7] = 0
                btn_job_study.setImageResource(R.drawable.bt_job_study_unactive)
            }
            changInput()
        }
        btn_job_industry.setOnClickListener {
            if (interestList[8] == 0) {
                interestList[8] = 1
                btn_job_industry.setImageResource(R.drawable.bt_job_industry_active)
            } else {
                interestList[8] = 0
                btn_job_industry.setImageResource(R.drawable.bt_job_industry_unactive)
            }
            changInput()
        }
        btn_job_edu.setOnClickListener {
            if (interestList[9] == 0) {
                interestList[9] = 1
                btn_job_edu.setImageResource(R.drawable.bt_job_literature_active)
            } else {
                interestList[9] = 0
                btn_job_edu.setImageResource(R.drawable.bt_job_literature_unactive)
            }
            changInput()
        }
        btn_job_construct.setOnClickListener {
            if (interestList[10] == 0) {
                interestList[10] = 1
                btn_job_construct.setImageResource(R.drawable.bt_job_construct_active)
            } else {
                interestList[10] = 0
                btn_job_construct.setImageResource(R.drawable.bt_job_construct_unactive)
            }
            changInput()
        }
        btn_job_medical.setOnClickListener {
            if (interestList[11] == 0) {
                interestList[11] = 1
                btn_job_media.setImageResource(R.drawable.bt_job_medical_active)
            } else {
                interestList[11] = 0
                btn_job_media.setImageResource(R.drawable.bt_job_medical_unactive)
            }
            changInput()
        }
        btn_job_media.setOnClickListener {
            if (interestList[12] == 0) {
                interestList[12] = 1
                btn_job_media.setImageResource(R.drawable.bt_job_art_active)
            } else {
                interestList[12] = 0
                btn_job_media.setImageResource(R.drawable.bt_job_art_unactive)
            }
            changInput()
        }
        btn_job_speciality.setOnClickListener {
            if (interestList[13] == 0) {
                interestList[13] = 1
                btn_job_media.setImageResource(R.drawable.bt_job_speciality_active)
            } else {
                interestList[13] = 0
                btn_job_media.setImageResource(R.drawable.bt_job_speciality_unactive)
            }
            changInput()
        }
    }

    private fun goToNext() {
        btn_job_save.setImageResource(R.drawable.bt_save_mypage_active)
        btn_job_save.setOnClickListener {
            for (i in 0..11) {
                if (interestList[i] == 1)
                    interestListServer = arrayOf(i.toByte())
            }
            Log.d("interest", "진희야 힘내" + interestListServer)
            toast("저장")
            finish()
        }
    }

    private fun changInput() {
        for (i in 0..11)
            if (interestList[i] == 1)
                check++

        if (check > 0) {
            goToNext()
            check = 0
        } else
            btn_job_save.setImageResource(R.drawable.bt_save_mypage_unactive)
    }

}