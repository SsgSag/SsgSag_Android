package com.sopt.appjam_sggsag

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_calendar_detail.*

class CalendarDetailActivity : AppCompatActivity() {

    private var event_name: String? = null
    private var category_num: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calendar_detail)

        setBtnOnClickListener()

        //행사 이름 받아오기
        event_name = intent.getStringExtra("name")
        calendar_detail_title.text = event_name.toString()

        //카테고리 받아오기
        category_num = intent.getStringExtra("category")
        if(category_num == "0"){
            text_calendar_detail_category.text = "공모전"
            text_calendar_detail_category.setTextColor(getColor(R.color.color0))
            calendar_detail_category_rectangle.setImageResource(R.drawable.label_task_blue)
        } else if(category_num == "1"){
            text_calendar_detail_category.text = "대외활동"
            text_calendar_detail_category.setTextColor(getColor(R.color.color1))
            calendar_detail_category_rectangle.setImageResource(R.drawable.label_task_skyblue)
        } else if(category_num == "2"){
            text_calendar_detail_category.text = "동아리"
            text_calendar_detail_category.setTextColor(getColor(R.color.color2))
            calendar_detail_category_rectangle.setImageResource(R.drawable.label_task_violet)
        } else if(category_num == "3"){
            text_calendar_detail_category.text = "교내활동"
            text_calendar_detail_category.setTextColor(getColor(R.color.color3))
            calendar_detail_category_rectangle.setImageResource(R.drawable.label_task_lavender)
        } else if(category_num == "4"){
            text_calendar_detail_category.text = "채용"
            text_calendar_detail_category.setTextColor(getColor(R.color.color4))
            calendar_detail_category_rectangle.setImageResource(R.drawable.label_task_coral_red)
        } else if(category_num == "5"){
            text_calendar_detail_category.text = "기타"
            text_calendar_detail_category.setTextColor(getColor(R.color.color5))
            calendar_detail_category_rectangle.setImageResource(R.drawable.label_task_pink)
        }
    }

    override fun onStart() {
        super.onStart()
        btn_calendar_detail_goto_website.setOnClickListener {
            Toast.makeText(this,"웹사이트로 이동",Toast.LENGTH_SHORT).show()
        }
    }

    private fun setBtnOnClickListener() {
        btn_calendar_detail_close.setOnClickListener {
            finish()
        }
    }



}
