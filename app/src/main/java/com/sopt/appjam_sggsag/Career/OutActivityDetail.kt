package com.sopt.appjam_sggsag.Career

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.widget.DatePicker
import com.sopt.appjam_sggsag.R
import kotlinx.android.synthetic.main.activity_out_detail.*
import org.jetbrains.anko.*
import java.util.*


class OutActivityDetail: AppCompatActivity() {



    val c = Calendar.getInstance()
    val c_year = c.get(Calendar.YEAR)
    val c_month = c.get(Calendar.MONTH)
    val c_day = c.get(Calendar.DAY_OF_MONTH)

    var startYear: String = c_year.toString()
    var startMonth: String = (c_month + 1).toString()
    var startDay: String = (c_day).toString()

    var endYear: String = c_year.toString()
    var endMonth: String = (c_month + 1).toString()
    var endDay: String = (c_day).toString()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_out_detail)
        getDateData()
        sendData()
    }

    private fun getDateData() {
        startYear = c_year.toString()
        startMonth = (c_month + 1).toString()
        startDay = (c_day).toString()

        endYear = c_year.toString()
        endMonth = (c_month + 1).toString()
        endDay = (c_day).toString()

        var check_date: Boolean = false


        btn_out_activity_close.setOnClickListener {
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
                    if (endYear > startYear) {
                        tv_activity_end.setText(endYear + "년 " + endMonth + "월 " + endDay + "일")
                        check_date = true
                    } else if (endYear == startYear) {
                        if (endMonth > startMonth) {
                            tv_activity_end.setText(endYear + "년 " + endMonth + "월 " + endDay + "일")
                            check_date = true
                        } else if (endMonth == startMonth) {
                            if (endDay >= startDay) {
                                tv_activity_end.setText(endYear + "년 " + endMonth + "월 " + endDay + "일")
                                check_date = true
                            } else {
                                toast("잘못된 날짜입니다.")
                                check_date = false
                            }
                        } else {
                            toast("잘못된 날짜입니다.")
                            check_date = false
                        }
                    } else {
                        toast("잘못된 날짜입니다.")
                        check_date = false
                    }
                }
                noButton { }
            }.show()
        }
    }


    private fun sendData() {
        btn_activity_save.setOnClickListener {
            var title: String = R.id.et_activity_title.toString()
            var notes: String = R.id.et_activity_note.toString()

            var start_data: String = startYear + "." + startMonth + "." + startDay
            var end_data: String = endYear + "." + endMonth + "." + endDay

            Log.e("진희야 힘내", "진희야 화이팅" + start_data + end_data)

            val fragment = Fragment()
            val bundle = Bundle()
            bundle.putString("title", title)

            fragment.arguments = bundle

            /*

            val intent = Intent(this@OutActivityDetail, Career1Fragment::class.java)
            intent.putExtra("title", title)
            intent.putExtra("notes", notes)
            intent.putExtra("start_data", start_data)
            intent.putExtra("end_data", end_data)

            startActivity(intent)

            */
            finish()
        }
    }
}
