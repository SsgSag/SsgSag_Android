package com.sopt.appjam_sggsag.MyPage

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.sopt.appjam_sggsag.MainActivity
import com.sopt.appjam_sggsag.MyApplication
import com.sopt.appjam_sggsag.Network.NetworkService
import com.sopt.appjam_sggsag.R
import com.sopt.appjam_sggsag.SignUp.SignUp3
import kotlinx.android.synthetic.main.activity_interest_area.*
import org.jetbrains.anko.startActivity

class InterestArea : AppCompatActivity() {

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
        setContentView(R.layout.activity_sign_up4)
        setOnBtnClickListener()
    }

    override fun onBackPressed() {
        startActivity<SignUp3>()
        finish()
        super.onBackPressed()
    }

    private fun setOnBtnClickListener() {

        iv_back_4.setOnClickListener {
            startActivity<SignUp3>()
            finish()
        }
        // 기획/아이디어
        btn_mypage_idea.setOnClickListener {
            if (interestList[0] == 0) {
                interestList[0] = 1
                btn_mypage_idea.setImageResource(R.drawable.bt_preference_idea_active)
            } else {
                interestList[0] = 0
                btn_mypage_idea.setImageResource(R.drawable.bt_preference_idea_unactive)
            }
            changInput()
        }

        btn_mypage_camera.setOnClickListener {
            if (interestList[7] == 0) {
                interestList[7] = 1
                btn_mypage_camera.setImageResource(R.drawable.bt_preference_camera_active)
            } else {
                interestList[7] = 0
                btn_mypage_camera.setImageResource(R.drawable.bt_preference_camera_unactive)
            }
            changInput()
        }

        btn_mypage_design.setOnClickListener {
            if (interestList[2] == 0) {
                interestList[2] = 1
                btn_mypage_design.setImageResource(R.drawable.bt_preference_design_active)
            } else {
                interestList[2] = 0
                btn_mypage_design.setImageResource(R.drawable.bt_preference_design_unactive)
            }
            changInput()
        }

        btn_mypage_marketing.setOnClickListener {
            if (interestList[5] == 0) {
                interestList[5] = 1
                btn_mypage_marketing.setImageResource(R.drawable.bt_preference_marketing_active)
            } else {
                interestList[5] = 0
                btn_mypage_marketing.setImageResource(R.drawable.bt_preference_marketing_unactive)
            }
            changInput()
        }
        btn_mypage_tech.setOnClickListener {
            if (interestList[11] == 0) {
                interestList[11] = 1
                btn_mypage_tech.setImageResource(R.drawable.bt_preference_tech_active)
            } else {
                interestList[11] = 0
                btn_mypage_tech.setImageResource(R.drawable.bt_preference_tech_unactive)
            }
            changInput()
        }
        btn_mypage_literature.setOnClickListener {
            if (interestList[3] == 0) {
                interestList[3] = 1
                btn_mypage_literature.setImageResource(R.drawable.bt_preference_literature_active)
            } else {
                interestList[3] = 0
                btn_mypage_literature.setImageResource(R.drawable.bt_preference_literature_unactive)
            }
            changInput()
        }
        btn_mypage_scholarship.setOnClickListener {
            if (interestList[10] == 0) {
                interestList[10] = 1
                btn_mypage_scholarship.setImageResource(R.drawable.bt_preference_sholarship_active)
            } else {
                interestList[10] = 0
                btn_mypage_scholarship.setImageResource(R.drawable.bt_preference_sholarship_unactive)
            }
            changInput()
        }
        btn_mypage_health.setOnClickListener {
            if (interestList[9] == 0) {
                interestList[9] = 1
                btn_mypage_health.setImageResource(R.drawable.bt_preference_health_active)
            } else {
                interestList[9] = 0
                btn_mypage_health.setImageResource(R.drawable.bt_preference_health_unactive)
            }
            changInput()
        }
        btn_mypage_startup.setOnClickListener {
            if (interestList[8] == 0) {
                interestList[8] = 1
                btn_mypage_startup.setImageResource(R.drawable.bt_preference_startup_active)
            } else {
                interestList[8] = 0
                btn_mypage_startup.setImageResource(R.drawable.bt_preference_startup_unactive)
            }
            changInput()
        }
        btn_mypage_art.setOnClickListener {
            if (interestList[4] == 0) {
                interestList[4] = 1
                btn_mypage_art.setImageResource(R.drawable.bt_preference_art_active)
            } else {
                interestList[4] = 0
                btn_mypage_art.setImageResource(R.drawable.bt_preference_art_unactive)
            }
            changInput()
        }
        btn_mypage_economy.setOnClickListener {
            if (interestList[1] == 0) {
                interestList[1] = 1
                btn_mypage_economy.setImageResource(R.drawable.bt_preference_economy_active)
            } else {
                interestList[1] = 0
                btn_mypage_economy.setImageResource(R.drawable.bt_preference_economy_unactive)
            }
            changInput()
        }
        btn_mypage_society.setOnClickListener {
            if (interestList[6] == 0) {
                interestList[6] = 1
                btn_mypage_society.setImageResource(R.drawable.bt_preference_society_active)
            } else {
                interestList[6] = 0
                btn_mypage_society.setImageResource(R.drawable.bt_preference_society_unactive)
            }
            changInput()
        }
    }

    private fun goToNext() {
        btn_start.setImageResource(R.drawable.bt_save_mypage_active)
        btn_start.setOnClickListener {
            for (i in 0..11) {
                if (interestList[i] == 1)
                    interestListServer= arrayOf(i.toByte())
            }
            Log.d("interest", "진희야 힘내" + interestListServer)
            startActivity<MainActivity>()
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
            btn_start.setImageResource(R.drawable.bt_save_mypage_unactive)
    }

}
