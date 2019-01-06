package com.sopt.appjam_sggsag.Fragment.Career

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sopt.appjam_sggsag.Adapter.Career.CareerRecyclerViewAdapter
import com.sopt.appjam_sggsag.Data.Career.CareerListData
import com.sopt.appjam_sggsag.R
import kotlinx.android.synthetic.main.fragment_career1.*

class Career1Fragment : Fragment() {
    private var career1Fragment: View? = null
    lateinit var careerRecyclerViewAdapter: CareerRecyclerViewAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        career1Fragment = inflater!!.inflate(R.layout.fragment_career1, container, false)

        return career1Fragment
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setRecyclerView()
    }

    private fun setRecyclerView(){
        var dataList: ArrayList<CareerListData> = ArrayList()
        dataList.add(CareerListData("안녕하세요", "2018.10", "이런 활동을 했습니다"))

        careerRecyclerViewAdapter = CareerRecyclerViewAdapter(activity!!, dataList)
        rv_career1_frag_activity_list.adapter = careerRecyclerViewAdapter
        rv_career1_frag_activity_list.layoutManager = LinearLayoutManager(activity)

    }



}