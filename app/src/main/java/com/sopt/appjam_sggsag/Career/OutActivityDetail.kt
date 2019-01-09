package com.sopt.appjam_sggsag.Career

import android.app.DatePickerDialog
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentTransaction
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.DatePicker
import android.widget.EditText
import com.sopt.appjam_sggsag.Adapter.Career.CareerRecyclerViewAdapter
import com.sopt.appjam_sggsag.Data.Career.CareerListData
import com.sopt.appjam_sggsag.Fragment.Career.Career1Fragment
import com.sopt.appjam_sggsag.R
import kotlinx.android.synthetic.main.activity_out_detail.*
import kotlinx.android.synthetic.main.activity_schedule_register.*
import kotlinx.android.synthetic.main.fragment_career1.*
import org.jetbrains.anko.*
import java.util.*

class OutActivityDetail : AppCompatActivity() {

    private var titleTxt: EditText? = null
    private var contentTxt: EditText? = null

    val c = Calendar.getInstance()
    val c_year = c.get(Calendar.YEAR)
    val c_month = c.get(Calendar.MONTH)
    val c_day = c.get(Calendar.DAY_OF_MONTH)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_out_detail)
        setBtnOnClickListener()

        // edit text에서 타이틀과 내용 가져옴
        titleTxt = findViewById(R.id.et_activity_title) as EditText
        contentTxt = findViewById(R.id.et_activity_note) as EditText

        tv_activity_start.setText(c_year.toString() + "년 " + (c_month + 1) + "월 " + c_day + "일")
        tv_activity_end.setText(c_year.toString() + "년 " + (c_month + 1) + "월 " + c_day + "일")


    }

    private fun setBtnOnClickListener(){
        var startYear: String = c_year.toString()
        var startMonth: String = (c_month + 1).toString()
        var startDay: String = (c_day).toString()

        var endYear: String = c_year.toString()
        var endMonth: String = (c_month + 1).toString()
        var endDay: String = (c_day).toString()

        var check_date: Boolean = false


        btn_out_activity_close.setOnClickListener {
            finish()
        }
        btn_activity_save.setOnClickListener {
            //sendData(Career1Fragment.getInstance(titleTxt!!.text.toString(), "2018.09.01", contentTxt!!.text.toString()))
            finish()
        }

        tv_activity_start.setOnClickListener {

            alert {
                isCancelable = false
                lateinit var datePicker: DatePicker
                customView {
                    verticalLayout {
                        datePicker = datePicker {
                            // maxDate = System.currentTimeMillis()
                        }
                    }
                }
                yesButton {
                    startYear = "${datePicker.year}"
                    startMonth = "${datePicker.month + 1}"
                    startDay = "${datePicker.dayOfMonth}"
                    tv_activity_start.setText(startYear + "년 " + startMonth + "월 " + startDay + "일")
                }
                noButton { }
            }.show()
        }

        tv_activity_end.setOnClickListener {
            alert {
                isCancelable = false
                lateinit var datePicker: DatePicker
                customView {
                    verticalLayout {
                        datePicker = datePicker {

                        }
                    }
                }
                yesButton {
                    endYear = "${datePicker.year}"
                    endMonth = "${datePicker.month + 1}"
                    endDay = "${datePicker.dayOfMonth}"
                    if(endYear > startYear){
                        tv_activity_end.setText(endYear + "년 " + endMonth + "월 " + endDay + "일")
                        check_date = true
                    }
                    else if(endYear == startYear){
                        if(endMonth > startMonth){
                            tv_activity_end.setText(endYear + "년 " + endMonth + "월 " + endDay + "일")
                            check_date = true
                        }
                        else if(endMonth==startMonth){
                            if(endDay >= startDay){
                                tv_activity_end.setText(endYear + "년 " + endMonth + "월 " + endDay + "일")
                                check_date = true
                            }
                            else{
                                toast("잘못된 날짜입니다.")
                                check_date = false
                            }
                        }
                        else {
                            toast("잘못된 날짜입니다.")
                            check_date = false
                        }
                    }
                    else {
                        toast("잘못된 날짜입니다.")
                        check_date = false
                    }
                }
                noButton { }
            }.show()
        }
    }


/*
    private fun sendData(fragment: Fragment) {
        if(titleTxt!=null&&contentTxt!=null){
            val transaction : FragmentTransaction = supportFragmentManager.beginTransaction()
            transaction.add(R.id.fl_rv_career1_frag_activity_list, fragment)
            transaction.commit()
        }
    }*/


/*
    private fun addItem(){
        if (et_activity_title.text.toString().isNotEmpty() && et_activity_note.text.toString().isNotEmpty()){
            val position = careerRecyclerViewAdapter.itemCount
            careerRecyclerViewAdapter.dataList.add(CareerListData(et_activity_title.text.toString(), "2018.09.01", et_activity_note.text.toString()))
            careerRecyclerViewAdapter.notifyItemInserted(position)
        }
    }*/

}
