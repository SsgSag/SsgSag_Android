package com.sopt.appjam_sggsag.Fragment

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.support.v7.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import android.widget.Toast
import com.baoyz.swipemenulistview.SwipeMenu
import com.baoyz.swipemenulistview.SwipeMenuCreator
import com.baoyz.swipemenulistview.SwipeMenuItem
import com.baoyz.swipemenulistview.SwipeMenuListView
import com.sopt.appjam_sggsag.Adapter.CalendarRecyclerAdapter2
import com.sopt.appjam_sggsag.Data.CalendarDateData
import com.sopt.appjam_sggsag.Data.EventList
import com.sopt.appjam_sggsag.Interface.GetYearMonthTab
import com.sopt.appjam_sggsag.MyApplication
import com.sopt.appjam_sggsag.R
import kotlinx.android.synthetic.main.fragment_calendar_detail.*
import org.jetbrains.anko.support.v4.ctx
import org.jetbrains.anko.support.v4.toast


class CalendarDetailFragment2 : Fragment(),GetYearMonthTab{
    override fun getYearMonthTab(year: String, month: String) {
        //이거 아마 안쓸걸? 걍 비워두자
    }

    override fun onClick(year: Int, month: Int, day: String) {
        mArrayList.clear()
        val yyear = year
        val mmonth = month
        val dday = day

        //list에 표시할 정보 골라내기
        tv_todo_title2.setText((month+1).toString()+"월 "+dday+"일")
        var count = 0
        for(i in 0..scheduleList.size-1){
            if(scheduleList[i].year==yyear &&scheduleList[i].month==mmonth+1 && scheduleList[i].day.toString()==dday){
                mArrayList.add(EventList(year,mmonth+1,dday.toInt(),scheduleList[i].eventName,2))
                //변경사항 카테고리
//                var eee = mArrayList[i].eventName
                count++
            }
        }

    }

    lateinit var recyclerViewAdapter: CalendarRecyclerAdapter2

    //var dataList = (activity!!.application as MyApplication).dataList1
    //(activity!!.application as MyApplication).
    var dataList : ArrayList<CalendarDateData> = ArrayList()
    var scheduleList : ArrayList<EventList> = ArrayList()
    var mArrayList: ArrayList<EventList> = ArrayList()
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

        var listAdapter: CalendarDetailFragment2.ListDataAdapter = ListDataAdapter()
        listView2.adapter = listAdapter

        val creator = SwipeMenuCreator { menu ->
            // Create different menus depending on the view type
            val goodItem = SwipeMenuItem(getActivity())
            // set item background
            goodItem.background = ColorDrawable(
                Color.rgb(0x30, 0xB1,0xF5)
            )
            // set item width
            goodItem.width = 200
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
            deleteItem.width = 200
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

                            /*
                            goodItem.background = ColorDrawable(
                                Color.rgb(0x30, 0xB1,0xF5)
                            )
                            */
                        }
                        1 -> {
                            mArrayList!!.removeAt(position)
                            //서버에서 일정 삭제 요청.
                            listAdapter.notifyDataSetChanged()      //바뀌었다고 알려줌.
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
        recyclerViewAdapter = CalendarRecyclerAdapter2(activity!!, dataList,scheduleList, month,this)
        frag_calendar_detail_recycle_view.adapter = recyclerViewAdapter
        frag_calendar_detail_recycle_view.layoutManager = GridLayoutManager(getActivity(), 7)


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
                holder?.mCategoryview = convertView!!.findViewById<View>(R.id.tv_rv_todo_category) as TextView
                holder?.mTitleview = convertView!!.findViewById<View>(R.id.tv_rv_todo_title) as TextView
                holder?.mPeriodview = convertView!!.findViewById<View>(R.id.tv_rv_todo_period) as TextView
                convertView.tag = holder
            } else {
                holder = convertView.tag as ViewHolder
            }

            when (mArrayList.get(position).category) {
                0 -> {
                    holder?.mCategoryview!!.text = "공모전"
                    holder?.mCategoryview!!.setTextColor(ContextCompat.getColor(ctx, R.color.color0))
                }
                1 -> {
                    holder?.mCategoryview!!.text = "대외활동"
                    holder?.mCategoryview!!.setTextColor(ContextCompat.getColor(ctx, R.color.color1))
                }
                2 -> {
                    holder?.mCategoryview!!.text = "동아리"
                    holder?.mCategoryview!!.setTextColor(ContextCompat.getColor(ctx, R.color.color2))
                }
                3 -> {
                    holder?.mCategoryview!!.text = "교내활동"
                    holder?.mCategoryview!!.setTextColor(ContextCompat.getColor(ctx, R.color.color3))
                }
                4 -> {
                    holder?.mCategoryview!!.text = "채용"
                    holder?.mCategoryview!!.setTextColor(ContextCompat.getColor(ctx, R.color.color4))
                }
                5 -> {
                    holder?.mCategoryview!!.text = "기타"
                    holder?.mCategoryview!!.setTextColor(ContextCompat.getColor(ctx, R.color.color5))
                }
            }
            holder?.mTitleview!!.text = mArrayList!!.get(position).eventName

            //계산코드
            holder?.mPeriodview!!.text = (mArrayList!!.get(position).month).toString() + "." +
                    mArrayList!!.get(position).day.toString() + " ~ "

            return convertView
        }

        internal inner class ViewHolder {
            var mCategoryview: TextView? = null
            var mTitleview: TextView? = null
            var mPeriodview: TextView? = null

        }
    }
}