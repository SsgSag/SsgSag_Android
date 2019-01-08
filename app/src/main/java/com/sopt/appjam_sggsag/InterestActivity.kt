package com.sopt.appjam_sggsag

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_interest.*

class InterestActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_interest)
        setBtnOnClickListener()
    }

    private fun setBtnOnClickListener(){
        btn_interest_close.setOnClickListener {
            finish()
        }
    }
}
