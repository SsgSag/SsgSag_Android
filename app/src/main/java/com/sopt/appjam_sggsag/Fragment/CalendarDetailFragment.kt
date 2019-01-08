package com.sopt.appjam_sggsag.Fragment

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AlphaAnimation
import android.widget.BaseAdapter
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import com.baoyz.swipemenulistview.SwipeMenu
import com.baoyz.swipemenulistview.SwipeMenuCreator
import com.baoyz.swipemenulistview.SwipeMenuItem
import com.baoyz.swipemenulistview.SwipeMenuListView
import com.sopt.appjam_sggsag.Adapter.CalendarRecyclerAdapter
import com.sopt.appjam_sggsag.Adapter.CalendarRecyclerAdapter2
import com.sopt.appjam_sggsag.Data.CalendarDateData
import com.sopt.appjam_sggsag.Data.EventList
import com.sopt.appjam_sggsag.Interface.GetYearMonthTab
import com.sopt.appjam_sggsag.MyApplication

import com.sopt.appjam_sggsag.R
import kotlinx.android.synthetic.main.fragment_calendar_detail.*


class CalendarDetailFragment : Fragment(), GetYearMonthTab {

    lateinit var recyclerViewAdapter: CalendarRecyclerAdapter
    lateinit var recyclerViewAdapter3: CalendarRecyclerAdapter2

    var mArrayList: java.util.ArrayList<String>? = java.util.ArrayList()

    var yyear : Int = 0
    var mmonth : Int = 0
    var dday : String = ""
    override fun getYearMonthTab(year: String, month: String) {

    }

    override fun onClick(year: Int, month: Int, day: String) {
        yyear = year
        mmonth = month
        dday = day

        val params =
            LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 689)
        val animation = AlphaAnimation(0f, 1f)
        animation.duration = 400
        frag_calendar_detail_recycle_view.layoutParams = params
        recyclerViewAdapter3 = CalendarRecyclerAdapter2(activity!!, dataList, scheduleList, month,this)
        frag_calendar_detail_recycle_view.adapter = recyclerViewAdapter3

        frag_calendar_detail_recycle_view.layoutManager = GridLayoutManager(getActivity(), 7)
        ll_todo_all_list.visibility = View.VISIBLE
        ll_todo_all_list.setAnimation(animation)


    }


    var dataList: ArrayList<CalendarDateData> = ArrayList()
    var scheduleList: ArrayList<EventList> = ArrayList()
    var month: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            month = it.getInt("diff")

        }


    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View = inflater.inflate(R.layout.fragment_calendar_detail, container, false)
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setOnClickListener()
        dataList = (activity!!.application as MyApplication).dataList1
        scheduleList = (activity!!.application as MyApplication).eventList1
        setRecycleView()

        listView2.setSwipeDirection(SwipeMenuListView.DIRECTION_LEFT)       //왼쪽 모션 추가

        //scheduleList 받아와서 날짜로 검사해서, 그 size로 for문 돌게.
        //1. scheduleList 받아오기.
        var count = 0
        for(i in 0..scheduleList.size-1){
            if(scheduleList[i].year==yyear &&scheduleList[i].month==mmonth+1 && scheduleList[i].day.toString()==dday ){
                count++
            }
        }
        for (i in 0..count-1) {
            mArrayList?.add("왜 이게 안뜨지?ㅇㅅㅇ")
        }
        var listAdapter: ListDataAdapter = ListDataAdapter()
        listView2.adapter = listAdapter

        val creator = SwipeMenuCreator { menu ->
            // Create different menus depending on the view type
            val goodItem = SwipeMenuItem(getActivity())
            // set item background
            goodItem.background = ColorDrawable(
                Color.rgb(0x30, 0xB1,0xF5)
            )
            // set item width
            goodItem.width = 127
            // set a icon
            goodItem.setIcon(R.drawable.ic_task_complete)
            // add to menu
            menu.addMenuItem(goodItem)

            // create "delete" item
            val deleteItem = SwipeMenuItem(
                getActivity()
            )
            // set item background
            deleteItem.background = ColorDrawable(
                Color.rgb(0xE4,0xE4, 0xE4)
            )
            // set item width
            deleteItem.width = 127
            // set a icon
            deleteItem.setIcon(R.drawable.ic_task_delete)
            // add to menu
            menu.addMenuItem(deleteItem)
        }
        listView2.setMenuCreator(creator)

        listView2.setOnMenuItemClickListener(
            object : SwipeMenuListView.OnMenuItemClickListener {
                override fun onMenuItemClick(position: Int, menu: SwipeMenu, index: Int): Boolean {

                    when (index) {
                        0 -> {
                            Toast.makeText(activity!!, position.toString()+"지원완료", Toast.LENGTH_SHORT).show()
                           // mArrayList[position]
                            /*
                            goodItem.background = ColorDrawable(
                                Color.rgb(0x30, 0xB1,0xF5)
                            )
                            */
                        }
                        1 -> {
                            mArrayList!!.removeAt(position)
                            listAdapter.notifyDataSetChanged()
                            Toast.makeText(activity!!, "Item deleted", Toast.LENGTH_SHORT).show()
                        }
                    }
                    return true
                }
            })

        listView2.setOnMenuStateChangeListener(object : SwipeMenuListView.OnMenuStateChangeListener {
            override fun onMenuOpen(position: Int) {

            }

            override fun onMenuClose(position: Int) {

            }
        })

        listView2.setOnSwipeListener(object : SwipeMenuListView.OnSwipeListener {
            override fun onSwipeStart(position: Int) {

            }

            override fun onSwipeEnd(position: Int) {

            }
        })
    }

    private fun setRecycleView() {
        //임시데이터

        //리사이클러뷰 어댑터를 만들어서 아래처럼 고대로 하면 돼! 그 리사이클러뷰 어댑터에서 ctx는 activity!! 이거 넘겨주면되고!

        recyclerViewAdapter = CalendarRecyclerAdapter(activity!!, dataList, scheduleList, month, this)
        frag_calendar_detail_recycle_view.adapter = recyclerViewAdapter
        frag_calendar_detail_recycle_view.layoutManager = GridLayoutManager(getActivity(), 7)


    }

    private fun setOnClickListener() {
        iv_big_calendar2.setOnClickListener {
            //달력 커지게 하는 이벤트.
            //대신 중요한 점은 보고있던 달력 그대로 넘어가야 한다는 점.
            val params =
                LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT)

            val animation2 = AlphaAnimation(0f, 1f)
            animation2.duration = 600
            frag_calendar_detail_recycle_view.layoutParams = params

            ll_todo_all_list.visibility = View.GONE
            ll_todo_all_list.setAnimation(animation2)
            setRecycleView()
        }
    }


    inner internal class ListDataAdapter : BaseAdapter() {

        var holder: ViewHolder? = null

        override fun getCount(): Int {
            return mArrayList!!.size
        }

        override fun getItem(i: Int): Any? {
            return null
        }

        override fun getItemId(i: Int): Long {
            return 0
        }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
            var convertView = convertView

            if (convertView == null) {
                holder = ViewHolder()
                convertView = layoutInflater.inflate(R.layout.list_item, null)
                // holder.mTextview
                holder?.mTextview = convertView!!.findViewById<View>(R.id.tv_rv_todo_category) as TextView
                convertView.tag = holder
            } else {
                holder = convertView.tag as ViewHolder
            }
            holder?.mTextview!!.text = mArrayList!!.get(position)
            return convertView
        }

        internal inner class ViewHolder {
            var mTextview: TextView? = null
        }
    }

}
