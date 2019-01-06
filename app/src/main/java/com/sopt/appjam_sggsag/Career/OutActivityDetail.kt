package com.sopt.appjam_sggsag.Career

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.sopt.appjam_sggsag.Adapter.Career.CareerRecyclerViewAdapter
import com.sopt.appjam_sggsag.Data.Career.CareerListData
import com.sopt.appjam_sggsag.R
import kotlinx.android.synthetic.main.activity_out_detail.*
import kotlinx.android.synthetic.main.fragment_career1.*

class OutActivityDetail : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_out_detail)
        setBtnOnClickListener()
    }


    private fun setBtnOnClickListener(){
        btn_out_activity_close.setOnClickListener {
            finish()
        }
        btn_activity_save.setOnClickListener {
            //addItem()
            finish()
        }
    }
/*
    private fun addItem(){
        if (et_activity_title.text.toString().isNotEmpty() && et_activity_note.text.toString().isNotEmpty()){
            val position = careerRecyclerViewAdapter.itemCount
            careerRecyclerViewAdapter.dataList.add(CareerListData(et_activity_title.text.toString(), "2018.09.01", et_activity_note.text.toString()))
            careerRecyclerViewAdapter.notifyItemInserted(position)
        }
    }*/
}
