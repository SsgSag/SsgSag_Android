package com.sopt.appjam_sggsag

import android.app.DatePickerDialog
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import com.sopt.appjam_sggsag.DB.SharedPreferenceController
import com.sopt.appjam_sggsag.Data.EventList
import com.sopt.appjam_sggsag.Fragment.tempcount
import com.sopt.appjam_sggsag.Network.NetworkService
import com.sopt.appjam_sggsag.Post.PostCalendarResponse
import com.sopt.appjam_sggsag.Post.PostCalendaraddResponse
import com.sopt.appjam_sggsag.R.id.et_schedule_title
import com.sopt.appjam_sggsag.R.id.tv_date_start
import kotlinx.android.synthetic.main.activity_schedule_register.*
import org.jetbrains.anko.*
import org.jetbrains.anko.support.v4.toast
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*
import javax.xml.datatype.DatatypeConstants.MONTHS

class ScheduleRegisterActivity : AppCompatActivity() {


    val networkService: NetworkService by lazy {
        MyApplication.instance.networkService
    }

    val c = Calendar.getInstance()
    val c_year = c.get(Calendar.YEAR)
    val c_month = c.get(Calendar.MONTH)
    val c_day = c.get(Calendar.DAY_OF_MONTH)
    var check_category = 10

    var startYear: String = c_year.toString()
    var startMonth: String = (c_month + 1).toString()
    var startDay: String = (c_day).toString()
    var startHour: String = ""
    var startMin: String = ""

    var endYear: String = c_year.toString()
    var endMonth: String = (c_month + 1).toString()
    var endDay: String = (c_day).toString()
    var endHour: String = ""
    var endMin: String = ""

    var check_contest: Boolean = false;
    var check_activity: Boolean = false;
    var check_club: Boolean = false;
    var check_school: Boolean = false;
    var check_career: Boolean = false;
    var check_extra: Boolean = false;

    var check_date: Boolean = false;
    var check_time: Boolean = false;

    var eventList: ArrayList<EventList> = ArrayList()

    // var s = (this.application as MyApplication).someVariable
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_schedule_register)
        tv_date_start.setText(c_year.toString() + "년 " + (c_month + 1) + "월 " + c_day + "일")
        tv_date_end.setText(c_year.toString() + "년 " + (c_month + 1) + "월 " + c_day + "일")
        // setSpinner()
        setOnClickListener()
        eventList = (this.application as MyApplication).eventList1
        // var dataList : ArrayList<CalendarDateData> = CalendarDetailFragment.dataList

        check_category = 10
    }


    private fun setOnClickListener() {

        tv_date_start.setOnClickListener {
            //datePicker
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
                    tv_date_start.setText(startYear + "년 " + startMonth + "월 " + startDay + "일")
                }
                noButton { }
            }.show()
        }

        tv_date_end.setOnClickListener {
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
                        tv_date_end.setText(endYear + "년 " + endMonth + "월 " + endDay + "일")
                        check_date = true
                    } else if (endYear == startYear) {
                        if (endMonth > startMonth) {
                            tv_date_end.setText(endYear + "년 " + endMonth + "월 " + endDay + "일")
                            check_date = true
                        } else if (endMonth == startMonth) {
                            if (endDay >= startDay) {
                                tv_date_end.setText(endYear + "년 " + endMonth + "월 " + endDay + "일")
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


        tv_time_start.setOnClickListener {
            alert {
                isCancelable = false
                lateinit var timePicker: TimePicker
                customView {
                    verticalLayout {
                        timePicker = timePicker {}
                    }
                }
                yesButton {
                    startHour = "${timePicker.hour}"
                    startMin = "${timePicker.minute}"
                    //"${timePicker.}"
                    if (startHour.toInt() > 12) {
                        if (startMin.length == 1) {
                            tv_time_start.setText("오후 " + (startHour.toInt() - 12) + ":0" + startMin)
                        } else {
                            tv_time_start.setText("오후 " + (startHour.toInt() - 12) + ":" + startMin)
                        }
                    } else if (startHour.toInt() < 12) {
                        if (startMin.length == 1) {
                            tv_time_start.setText("오전 " + startHour + ":0" + startMin)
                        } else {
                            tv_time_start.setText("오전 " + startHour + ":" + startMin)
                        }
                    } else {
                        if (startMin.length == 1) {
                            tv_time_start.setText("오후 " + (startHour) + ":0" + startMin)
                        } else {
                            tv_time_start.setText("오후 " + (startHour) + ":" + startMin)
                        }
                    }
                }
                noButton { }
            }.show()

        }

        tv_time_end.setOnClickListener {
            alert {
                isCancelable = false
                lateinit var timePicker: TimePicker
                customView {
                    verticalLayout {
                        timePicker = timePicker {}
                    }
                }
                yesButton {
                    endHour = "${timePicker.hour}"
                    endMin = "${timePicker.minute}"

                    if (startYear == endYear && startMonth == endMonth && startDay == endDay) {
                        if (startHour < endHour) {
                            if (endHour.toInt() > 12) {
                                if (endMin.length == 1) {
                                    tv_time_end.setText("오후 " + (endHour.toInt() - 12) + ":0" + endMin)
                                } else {
                                    tv_time_end.setText("오후 " + (endHour.toInt() - 12) + ":" + endMin)
                                }
                                check_time = true
                            } else if (endHour.toInt() < 12) {
                                if (endMin.length == 1) {
                                    tv_time_end.setText("오전 " + endHour + ":0" + endMin)
                                } else {
                                    tv_time_end.setText("오전 " + endHour + ":" + endMin)
                                }
                                check_time = true
                            } else {
                                if (endMin.length == 1) {
                                    tv_time_end.setText("오후 " + endHour + ":0" + endMin)
                                } else {
                                    tv_time_end.setText("오후 " + endHour + ":" + endMin)
                                }
                                check_time = true
                            }

                        } else if (startHour == endHour) {
                            if (startMin > endMin) {
                                toast("잘못된 시간입니다.")
                                check_time = false
                            } else {
                                if (endHour.toInt() > 12) {
                                    if (endMin.length == 1) {
                                        tv_time_end.setText("오후 " + (endHour.toInt() - 12) + ":0" + endMin)
                                    } else {
                                        tv_time_end.setText("오후 " + (endHour.toInt() - 12) + ":" + endMin)
                                    }
                                    check_time = true
                                } else if (endHour.toInt() < 12) {
                                    if (endMin.length == 1) {
                                        tv_time_end.setText("오전 " + endHour + ":0" + endMin)
                                    } else {
                                        tv_time_end.setText("오전 " + endHour + ":" + endMin)
                                    }
                                    check_time = true
                                } else {
                                    if (endMin.length == 1) {
                                        tv_time_end.setText("오후 " + endHour + ":0" + endMin)
                                    } else {
                                        tv_time_end.setText("오후 " + endHour + ":" + endMin)
                                    }
                                    check_time = true
                                }
                            }
                        } else if (startHour > endHour) {
                            toast("잘못된 시간입니다.")
                            check_time = false
                        }
                    } else {
                        if (endHour.toInt() > 12) {
                            if (endMin.length == 1) {
                                tv_time_end.setText("오후 " + (endHour.toInt() - 12) + ":0" + endMin)
                            } else {
                                tv_time_end.setText("오후 " + (endHour.toInt() - 12) + ":" + endMin)
                            }
                            check_time = true
                        } else if (endHour.toInt() < 12) {
                            if (endMin.length == 1) {
                                tv_time_end.setText("오전 " + endHour + ":0" + endMin)
                            } else {
                                tv_time_end.setText("오전 " + endHour + ":" + endMin)
                            }
                            check_time = true
                        } else {
                            if (endMin.length == 1) {
                                tv_time_end.setText("오후 " + endHour + ":0" + endMin)
                            } else {
                                tv_time_end.setText("오후 " + endHour + ":" + endMin)
                            }
                            check_time = true
                        }
                    }

                }
                noButton { }
            }.show()
        }

        btn_exit.setOnClickListener {
            //eventList.add(EventList(year_spinner.getSelectedItem().toString().toInt(),month_spinner.getSelectedItem().toString().toInt(),day_spinner.getSelectedItem().toString().toInt(),register_event_name.text.toString(),3))
            //category3 넣어놨음.이거 받아와야함.

            startActivity<MainActivity>()
            finish()
        }



        ll_category_contest.setOnClickListener {
            if (!check_contest) {
                iv_category_contest.setImageResource(R.drawable.ic_category_contest_active)
                check_contest = true
                check_category = 0
            } else {
                iv_category_contest.setImageResource(R.drawable.ic_category_contest)
                check_contest = false
                check_category = 10
            }
        }
        ll_category_activity.setOnClickListener {
            if (!check_activity) {
                iv_category_activity.setImageResource(R.drawable.ic_category_activity_active)
                check_activity = true
                check_category = 1
            } else {
                iv_category_activity.setImageResource(R.drawable.ic_category_activity)
                check_activity = false
                check_category = 10
            }
        }
        ll_category_club.setOnClickListener {
            if (!check_club) {
                iv_category_club.setImageResource(R.drawable.ic_category_club_active)
                check_category = 2
                check_club = true
            } else {
                iv_category_club.setImageResource(R.drawable.ic_category_club)
                check_club = false
                check_category = 10
            }
        }
        ll_category_school.setOnClickListener {
            if (!check_school) {
                iv_category_school.setImageResource(R.drawable.ic_category_school_active)
                check_category = 3
                check_school = true
            } else {
                iv_category_school.setImageResource(R.drawable.ic_category_school)
                check_school = false
                check_category = 10
            }
        }
        ll_category_career.setOnClickListener {
            if (!check_career) {
                iv_category_career.setImageResource(R.drawable.ic_category_career_active)
                check_category = 4
                check_career = true
            } else {
                iv_category_career.setImageResource(R.drawable.ic_category_career)
                check_career = false
                check_category = 10
            }
        }
        ll_category_extra.setOnClickListener {
            if (!check_extra) {
                iv_category_extra.setImageResource(R.drawable.ic_category_extra_active)
                check_extra = true
                check_category = 5
            } else {
                iv_category_extra.setImageResource(R.drawable.ic_category_extra)
                check_extra = false
                check_category = 10
            }
        }
        btn_schedule_save.setOnClickListener {
            getCalendaraddResponse()
            finish()
        }
    }

    private fun getCalendaraddResponse() {
        var categoryIdx = check_category
        var manualName = et_schedule_title.text
        var manualDetail = et_note.text
        var manualStartDate = startYear + "-" + startMonth + "-" + startDay + " " + startHour + ":" + startMin
        var manualEndDate = endYear + "-" + endMonth + "-" + endDay + " " + endHour + ":" + endMin
        var isAlarm = 0
        var jsonObject = JSONObject()
        jsonObject.put("categoryIdx", categoryIdx)
        jsonObject.put("manualName", manualName)
        jsonObject.put("manualDetail", manualDetail)
        jsonObject.put("manualStartDate", manualStartDate)
        jsonObject.put("manualEndDate", manualEndDate)
        jsonObject.put("isAlarm", isAlarm)
        val gsonObject = JsonParser().parse(jsonObject.toString()) as JsonObject

        val token = SharedPreferenceController.getAuthorization(this)
        val postCalendaraddResponse: Call<PostCalendaraddResponse> = networkService.postCalendaraddResponse(
            "application/json",
            "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJEb0lUU09QVCIsInVzZXJfaWR4IjoxfQ.5lCvAqnzYP4-2pFx1KTgLVOxYzBQ6ygZvkx5jKCFM08"
            , gsonObject
        )

        postCalendaraddResponse.enqueue(object : Callback<PostCalendaraddResponse> {
            override fun onFailure(call: Call<PostCalendaraddResponse>, t: Throwable) {
                Log.e("manualadd fail", t.toString())
            }
            override fun onResponse(call: Call<PostCalendaraddResponse>, response: Response<PostCalendaraddResponse>) {
                if (response.isSuccessful) {
                    Log.e("manualadd success", response.body()!!.message)
                    toast(response.body()!!.message)
                    response.body()?.status

                }
            }
        })
    }
}




