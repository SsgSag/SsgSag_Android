package com.sopt.appjam_sggsag.Fragment.Career

import android.app.Activity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import com.sopt.appjam_sggsag.Adapter.Career.CareerRecyclerViewAdapter
import com.sopt.appjam_sggsag.Career.OutActivityDetail
import com.sopt.appjam_sggsag.Data.Career.CareerListData
import com.sopt.appjam_sggsag.R
import kotlinx.android.synthetic.main.fragment_career1.*
import org.jetbrains.anko.find
import android.content.Intent
import org.jetbrains.anko.support.v4.startActivityForResult
import java.util.*


class Career1Fragment : Fragment() {

    var REQUEST_CODE_OUT_ACTIVITY: Int = 111
    private var career1Fragment: View? = null
    lateinit var careerRecyclerViewAdapter: CareerRecyclerViewAdapter
    private var titleTxt: String? = null
    private var startDate: String? = null
    private var endDate: String? = null
    private var contentTxt: String? = null
    var dataList: ArrayList<CareerListData> = ArrayList()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        career1Fragment = inflater!!.inflate(R.layout.fragment_career1, container, false)
        setBtnOnClickListener()

        return career1Fragment
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        careerRecyclerViewAdapter = CareerRecyclerViewAdapter(activity!!, dataList)

        if(careerRecyclerViewAdapter.dataList.size==0)
            rl_empty_career.visibility=View.VISIBLE
    }

    private fun setBtnOnClickListener() {
        val add_activity: RelativeLayout = career1Fragment!!.find(R.id.btn_add_activity)
        add_activity.setOnClickListener {
            startActivityForResult<OutActivityDetail>(REQUEST_CODE_OUT_ACTIVITY)

        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == REQUEST_CODE_OUT_ACTIVITY) {
            if (resultCode == Activity.RESULT_OK) {

                titleTxt = data!!.getStringExtra("title").toString()
                startDate = data!!.getStringExtra("start_data").toString()
                endDate = data!!.getStringExtra("end_data").toString()
                contentTxt = data!!.getStringExtra("notes").toString()

                dataList.add(CareerListData(titleTxt, startDate + " ~ " + endDate, contentTxt))

//                careerRecyclerViewAdapter = CareerRecyclerViewAdapter(activity!!, dataList)
                rv_career1_frag_activity_list.adapter = careerRecyclerViewAdapter
                rv_career1_frag_activity_list.layoutManager = LinearLayoutManager(activity)

                invisibleImage()
            }
        }
    }

    fun invisibleImage() {
        rl_empty_career.visibility = View.INVISIBLE
    }
}