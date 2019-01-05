package com.sopt.appjam_sggsag.Fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sopt.appjam_sggsag.Adapter.CalendarRecyclerAdapter2
import com.sopt.appjam_sggsag.Data.CalendarDateData
import com.sopt.appjam_sggsag.Data.EventList
import com.sopt.appjam_sggsag.MyApplication
import com.sopt.appjam_sggsag.R
import kotlinx.android.synthetic.main.fragment_calendar_detail.*


class CalendarDetailFragment2 : Fragment(){

    lateinit var recyclerViewAdapter: CalendarRecyclerAdapter2

    //var dataList = (activity!!.application as MyApplication).dataList1
    //(activity!!.application as MyApplication).
    var dataList : ArrayList<CalendarDateData> = ArrayList()
    var scheduleList : ArrayList<EventList> = ArrayList()
    var month: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            month = it.getInt("diff")

        }

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View = inflater.inflate(R.layout.fragment_calendar_detail, container, false)
        //   toast(month.toString())
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        dataList = (activity!!.application as MyApplication).dataList1
        scheduleList = (activity!!.application as MyApplication).eventList1
        setRecycleView()
    }

    private fun setRecycleView() {
        //임시데이터



        //리사이클러뷰 어댑터를 만들어서 아래처럼 고대로 하면 돼! 그 리사이클러뷰 어댑터에서 ctx는 activity!! 이거 넘겨주면되고!
        recyclerViewAdapter = CalendarRecyclerAdapter2(activity!!, dataList,scheduleList, month)
        frag_calendar_detail_recycle_view.adapter = recyclerViewAdapter
        frag_calendar_detail_recycle_view.layoutManager = GridLayoutManager(getActivity(), 7)


    }

}