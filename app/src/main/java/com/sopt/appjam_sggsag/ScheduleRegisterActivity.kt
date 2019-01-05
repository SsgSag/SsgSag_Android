package com.sopt.appjam_sggsag

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.sopt.appjam_sggsag.Data.EventList
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

class ScheduleRegisterActivity : AppCompatActivity() {


    var eventList: ArrayList<EventList> = ArrayList()

    // var s = (this.application as MyApplication).someVariable
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_schedule_register)
       // setSpinner()
      //  setOnClickListener()
        eventList =  (this.application as MyApplication).eventList1
        // var dataList : ArrayList<CalendarDateData> = CalendarDetailFragment.dataList

    }
/*
    private fun setSpinner(){
        //var yearSpinner: Spinner = year_spinner
        // var monthSpinner : Spinner = month_spinner
        // var daySpinner : Spinner = day_spinner
        val yearArray = arrayOf(2017,2018,2019,2020)
        year_spinner.setSelection(1)

        //year_spinner.adapter = ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,yearArray)

        year_spinner.adapter = ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,yearArray)
        year_spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{

            override fun onNothingSelected(p0: AdapterView<*>?) {
                toast("나도 모르겠다 여기 뭐 들어가야하는지")
            }

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                year_spinner.setSelection(p2)
            }
        }

        val monthArray = arrayOf(1,2,3,4,5,6,7,8,9,10,11,12)
        month_spinner.setSelection(1)
        month_spinner.adapter = ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,monthArray)
        month_spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{

            override fun onNothingSelected(p0: AdapterView<*>?) {
                toast("나도 모르겠다 여기 뭐 들어가야하는지")
            }

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                month_spinner.setSelection(p2)
            }
        }
        val dayArray = arrayOf(1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31)


        day_spinner.setSelection(1)
        day_spinner.adapter = ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,dayArray)
        day_spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{

            override fun onNothingSelected(p0: AdapterView<*>?) {
                //toast("나도 모르겠다 여기 뭐 들어가야하는지")
            }

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                day_spinner.setSelection(p2)
            }
        }
    }
*/
    /*
    private fun setOnClickListener(){
        //addFragment
        btn_act_sche_reg.setOnClickListener{
            eventList.add(EventList(year_spinner.getSelectedItem().toString().toInt(),month_spinner.getSelectedItem().toString().toInt(),day_spinner.getSelectedItem().toString().toInt(),register_event_name.text.toString(),3))
            //category3 넣어놨음.이거 받아와야함.
            startActivity<MainActivity>()
            finish()
        }
    }
    */




}
