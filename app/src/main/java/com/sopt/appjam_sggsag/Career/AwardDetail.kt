package com.sopt.appjam_sggsag.Career

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.DatePicker
import com.sopt.appjam_sggsag.Fragment.Career.Career1Fragment
import com.sopt.appjam_sggsag.R
import kotlinx.android.synthetic.main.activity_award_detail.*
import kotlinx.android.synthetic.main.activity_out_detail.*
import org.jetbrains.anko.*
import java.util.*

class AwardDetail : AppCompatActivity() {

    val c = Calendar.getInstance()
    val c_year = c.get(Calendar.YEAR)
    val c_month = c.get(Calendar.MONTH)
    val c_day = c.get(Calendar.DAY_OF_MONTH)

    var startYear: String = c_year.toString()
    var startMonth: String = (c_month + 1).toString()
    var startDay: String = (c_day).toString()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_award_detail)

        var year = c.get(Calendar.YEAR)
        var month = c.get(Calendar.MONTH) + 1
        var day = c.get(Calendar.DAY_OF_MONTH)

        tv_award_date.setText(year.toString() + "년 " + month.toString() + "월 " + day.toString() + "일")

        getDateData()
        sendData()
    }

    private fun getDateData() {
        startYear = c_year.toString()
        startMonth = (c_month + 1).toString()
        startDay = (c_day).toString()
        var check_date: Boolean = false


        btn_award_close.setOnClickListener {
            finish()
        }

        tv_award_date.setOnClickListener {

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
                    tv_award_date.setText(startYear + "년 " + startMonth + "월 " + startDay + "일")
                }
                noButton { }
            }.show()
        }
    }


    private fun sendData() {
        btn_award_save.setOnClickListener {
            var title: String = et_award_title.text.toString()
            var notes: String = et_award_note.text.toString()

            var start_data: String = startYear + "." + startMonth + "." + startDay

            if (title.isNotEmpty() && notes.isNotEmpty()) {
                val intent = Intent(this@AwardDetail, Career1Fragment::class.java)
                intent.putExtra("title", title)
                intent.putExtra("notes", notes)
                intent.putExtra("start_data", start_data)

                setResult(Activity.RESULT_OK, intent)
            }

            finish()
        }
    }
}
