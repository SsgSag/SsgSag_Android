package com.sopt.appjam_sggsag

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_calendar_detail.*

class CalendarDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calendar_detail)
    }

    override fun onStart() {
        super.onStart()
        btn_calendar_detail_goto_website.setOnClickListener {
            Toast.makeText(this,"웹사이트로 이동",Toast.LENGTH_SHORT).show()
        }
    }

}
