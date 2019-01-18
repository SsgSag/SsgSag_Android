package com.sopt.appjam_sggsag.Fragment

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.support.v7.widget.GridLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AlphaAnimation
import android.widget.*
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
import org.jetbrains.anko.support.v4.ctx
import org.jetbrains.anko.support.v4.toast
import com.baoyz.swipemenulistview.SwipeMenuLayout
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import com.sopt.appjam_sggsag.Adapter.year
import com.sopt.appjam_sggsag.CalendarDetailActivity
import com.sopt.appjam_sggsag.DB.SharedPreferenceController
import com.sopt.appjam_sggsag.Data.EventNameList
import com.sopt.appjam_sggsag.Network.NetworkService
import com.sopt.appjam_sggsag.Post.CalendarData
import com.sopt.appjam_sggsag.Post.PostCalendarResponse
import kotlinx.android.synthetic.*
import org.jetbrains.anko.support.v4.startActivity
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception
import java.lang.Thread.sleep

var tempcount = 0
var countMonth = 0

class CalendarDetailFragment : Fragment(), GetYearMonthTab {

    lateinit var recyclerViewAdapter: CalendarRecyclerAdapter
    lateinit var recyclerViewAdapter3: CalendarRecyclerAdapter2


    var mArrayList: ArrayList<EventList> = ArrayList()
    var mArrayList2: ArrayList<EventList> = ArrayList()


    var yyear: Int = 0
    var mmonth: Int = 0
    var dday: String = ""
    var listAdapter: ListDataAdapter = ListDataAdapter()
    var listAdapter2: ListDataAdapter2 = ListDataAdapter2()
    var listServer: ArrayList<CalendarData>? = ArrayList()

    val networkService: NetworkService by lazy {
        MyApplication.instance.networkService
    }

    // 혜원 투두리스트 통신 투두리스트 터치했을 때 액티비티로 정보 보내기
    private fun sendData() {
        listView2.setOnItemClickListener { parent, view, position, id ->

            var name: String = mArrayList[position].eventName
            var category_num: String = mArrayList[position].category.toString()

            val intent = Intent(context, CalendarDetailActivity::class.java)
            intent.putExtra("name", name)
            intent.putExtra("category", category_num)
            Log.e("혜원아 힘내","화이팅"+name)
            startActivityForResult(intent, 666)
        }
    }

    override fun getYearMonthTab(year: String, month: String) {

    }

    override fun onClick(year: Int, month: Int, day: String, position: Int) {
        mArrayList.clear()
        yyear = year
        mmonth = month
        dday = day
        val params =
            LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 689)
        val animation = AlphaAnimation(0f, 1f)
        animation.duration = 400
        frag_calendar_detail_recycle_view.layoutParams = params
        recyclerViewAdapter3 = CalendarRecyclerAdapter2(activity!!, dataList, scheduleList, month, this, position)
        frag_calendar_detail_recycle_view.adapter = recyclerViewAdapter3
        frag_calendar_detail_recycle_view.layoutManager = GridLayoutManager(getActivity(), 7)
        ll_todo_all_list.visibility = View.VISIBLE
        ll_todo_all_list.setAnimation(animation)
        tv_todo_title2.setText((month + 1).toString() + "월 " + dday + "일")

        var count = 0
        for (i in 0..scheduleList.size - 1) {
            if (scheduleList[i].year == yyear && scheduleList[i].month == mmonth + 1 && scheduleList[i].day.toString() == dday) {
                mArrayList.add(
                    EventList(
                        scheduleList[i].startDay,
                        scheduleList[i].endDay,
                        year,
                        mmonth + 1,
                        dday.toInt(),
                        scheduleList[i].eventName,
                        scheduleList[i].category,
                        scheduleList[i].dday
                    )
                )

                count++
            }
        }
/*
        for (j in 0..scheduleList.size - 1) {
            if (scheduleList[j].dday >= 0 && scheduleList[j].dday <= 30) {
                if (mArrayList2.size == 0) {
                    mArrayList2.add(
                        EventList(
                            scheduleList[j].startDay, scheduleList[j].endDay, year, mmonth + 1,
                            dday.toInt(), scheduleList[j].eventName, scheduleList[j].category, scheduleList[j].dday
                        ))
                } else {
                    for (i in 0..mArrayList2.size - 1) {
                        if (mArrayList2[i].eventName != scheduleList[j].eventName) {
                            mArrayList2.add(
                                EventList(
                                    scheduleList[j].startDay,
                                    scheduleList[j].endDay,
                                    year,
                                    mmonth + 1,
                                    dday.toInt(),
                                    scheduleList[j].eventName,
                                    scheduleList[j].category,
                                    scheduleList[j].dday
                                )
                            )
                        }
                    }
                }


            }
            /*
            mArrayList2.add(
                EventList( scheduleList[j].startDay,
                    scheduleList[j].endDay,
                    year,
                    mmonth + 1,
                    dday.toInt(),
                    scheduleList[j].eventName,
                    scheduleList[j].category,
                    scheduleList[j].dday)
            )*/
        }
*/
        sorting()   //dday순 정렬 끝
        for (i in 0..mArrayList2.size - 1)
            Log.e("mArrayList2", mArrayList2[i].dday.toString() + " " + mArrayList2.size)

        if (count > 4) {
            //표시 추가
        }
        //mArrayList = mArrayList2
        listAdapter.notifyDataSetChanged()
    }


    var dataList: ArrayList<CalendarDateData> = ArrayList()
    var scheduleList: ArrayList<EventList> = ArrayList()
    var month: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        countMonth = 0
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

        sendData()

        //mArrayList.add(EventList("2018-01-12","2018-01-13", 2018,1,1,"정연이생일",4,0))

        listView2.setSwipeDirection(SwipeMenuListView.DIRECTION_LEFT)       //왼쪽 모션 추가

        //scheduleList 받아와서 날짜로 검사해서, 그 size로 for문 돌게.
        //1. scheduleList 받아오기.


        listView2.adapter = listAdapter2

        listView2.adapter = listAdapter

        val creator = SwipeMenuCreator { menu ->
            // Create different menus depending on the view type
            val goodItem = SwipeMenuItem(getActivity())
            // set item background
            goodItem.background = ColorDrawable(Color.rgb(0x30, 0xB1, 0xF5))
            // set item width
            goodItem.width = 200
            // set a icon
            goodItem.setIcon(R.drawable.ic_task_complete)
            // add to menu
            menu.addMenuItem(goodItem)

            // create "delete" item
            val deleteItem = SwipeMenuItem(getActivity())
            // set item background
            deleteItem.background = ColorDrawable(Color.rgb(0xE4, 0xE4, 0xE4))
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
                            Toast.makeText(activity!!, position.toString() + "지원완료", Toast.LENGTH_SHORT).show()
                            val view = listView2 as SwipeMenuLayout

                            menu.getMenuItem(0).background =
                                    ColorDrawable(Color.rgb(0xE4, 0xE4, 0xE4))// .setIcon(R.drawable.ic_task_delete)
                        }
                        1 -> {
                            mArrayList!!.removeAt(position)
                            //서버에서 일정 삭제 요청.
                            getScheduleDelete()
                            listAdapter.notifyDataSetChanged()      //바뀌었다고 알려줌.
                            Toast.makeText(activity!!, "Item deleted", Toast.LENGTH_SHORT).show()
                        }
                    }
                    return true
                }
            })

        listView2.setOnMenuStateChangeListener(object : SwipeMenuListView.OnMenuStateChangeListener {
            override fun onMenuOpen(position: Int) {
                //   toast("onMenuOpen")
            }

            override fun onMenuClose(position: Int) {
                //   toast("onMenuClose")
            }
        })

        listView2.setOnSwipeListener(object : SwipeMenuListView.OnSwipeListener {
            override fun onSwipeStart(position: Int) {
                //  toast("onSwipeStart")
            }

            override fun onSwipeEnd(position: Int) {
                // toast("onSwipeEnd")
            }
        })
    }


    private fun getScheduleDelete() {

    }

    private fun setRecycleView() {
        if (countMonth == 0) {
            val params =
                LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 689)
            val animation = AlphaAnimation(0f, 1f)
            animation.duration = 400
            frag_calendar_detail_recycle_view.layoutParams = params

            //오류여기
            recyclerViewAdapter3 = CalendarRecyclerAdapter2(activity!!, dataList, scheduleList, month, this, 50)
            frag_calendar_detail_recycle_view.adapter = recyclerViewAdapter3
            frag_calendar_detail_recycle_view.layoutManager = GridLayoutManager(getActivity(), 7)
            countMonth++
        } else {
            val params =
                LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.MATCH_PARENT
                )
            val animation = AlphaAnimation(0f, 1f)
            animation.duration = 400
            frag_calendar_detail_recycle_view.layoutParams = params
            recyclerViewAdapter = CalendarRecyclerAdapter(activity!!, dataList, scheduleList, month, this)
            frag_calendar_detail_recycle_view.adapter = recyclerViewAdapter
            frag_calendar_detail_recycle_view.layoutManager = GridLayoutManager(getActivity(), 7)
        }


    }

    private fun setOnClickListener() {
        ll_todo_top_bar2.setOnClickListener {
            //달력 커지게 하는 이벤트.
            //대신 중요한 점은 보고있던 달력 그대로 넘어가야 한다는 점.
            val params =
                LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.MATCH_PARENT
                )

            val animation2 = AlphaAnimation(0f, 1f)
            animation2.duration = 600
            frag_calendar_detail_recycle_view.layoutParams = params

            ll_todo_all_list.visibility = View.GONE
            ll_todo_all_list.setAnimation(animation2)
            setRecycleView()
        }
    }


    private fun sorting() {
        var minIndex = 9999;

        var temp: EventList? = null
        for (i in 0..mArrayList2.size - 1) {
            minIndex = i
            for (j in i + 1..mArrayList2.size - 1) {
                if (mArrayList2[j].dday < mArrayList2[minIndex].dday)
                    minIndex = j    //
            }
            temp = mArrayList2[i]
            mArrayList2[i] = mArrayList2[minIndex]
            mArrayList2[minIndex] = temp
        }
    }

    inner class ListDataAdapter : BaseAdapter() {

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
                //        holder?.mDdayview = convertView!!.findViewById<View>(R.id.tv_rv_d_day3) as TextView
                holder?.mCategoryColorview =
                        convertView!!.findViewById<View>(R.id.iv_rv_todo_category_color) as ImageView
                convertView.tag = holder
            } else {
                holder = convertView.tag as ViewHolder
            }

            when (mArrayList.get(position).category) {
                0 -> {
                    holder?.mCategoryview!!.text = "공모전"
                    holder?.mCategoryview!!.setTextColor(ContextCompat.getColor(ctx, R.color.color0))
                    holder?.mCategoryColorview!!.setImageDrawable(
                        ContextCompat.getDrawable(
                            ctx,
                            R.drawable.label_task_blue
                        )
                    )
                }
                1 -> {
                    holder?.mCategoryview!!.text = "대외활동"
                    holder?.mCategoryview!!.setTextColor(ContextCompat.getColor(ctx, R.color.color1))
                    holder?.mCategoryColorview!!.setImageDrawable(
                        ContextCompat.getDrawable(
                            ctx,
                            R.drawable.label_task_skyblue
                        )
                    )
                }
                2 -> {
                    holder?.mCategoryview!!.text = "동아리"
                    holder?.mCategoryview!!.setTextColor(ContextCompat.getColor(ctx, R.color.color2))
                    holder?.mCategoryColorview!!.setImageDrawable(
                        ContextCompat.getDrawable(
                            ctx,
                            R.drawable.label_task_violet
                        )
                    )
                }
                3 -> {
                    holder?.mCategoryview!!.text = "교내활동"
                    holder?.mCategoryview!!.setTextColor(ContextCompat.getColor(ctx, R.color.color3))
                    holder?.mCategoryColorview!!.setImageDrawable(
                        ContextCompat.getDrawable(
                            ctx,
                            R.drawable.label_task_lavender
                        )
                    )
                }
                4 -> {
                    holder?.mCategoryview!!.text = "채용"
                    holder?.mCategoryview!!.setTextColor(ContextCompat.getColor(ctx, R.color.color4))
                    holder?.mCategoryColorview!!.setImageDrawable(
                        ContextCompat.getDrawable(
                            ctx,
                            R.drawable.label_task_coral_red
                        )
                    )
                }
                5 -> {
                    holder?.mCategoryview!!.text = "기타"
                    holder?.mCategoryview!!.setTextColor(ContextCompat.getColor(ctx, R.color.color5))
                    holder?.mCategoryColorview!!.setImageDrawable(
                        ContextCompat.getDrawable(
                            ctx,
                            R.drawable.label_task_pink
                        )
                    )
                }
            }
            holder?.mTitleview!!.text = mArrayList!!.get(position).eventName

            //계산코드
            holder?.mPeriodview!!.text = (mArrayList!!.get(position).startDay?.substring(5, 7)).toString() + "." +
                    mArrayList!!.get(position).startDay?.substring(8, 10).toString() + " ~ " +
                    (mArrayList!!.get(position).endDay?.substring(5, 7)).toString() + "." +
                    mArrayList!!.get(position).endDay?.substring(8, 10).toString()

            //   holder?.mDdayview!!.text = (mArrayList!!.get(position).dday).toString()

            return convertView
        }

        inner class ViewHolder {
            var mCategoryview: TextView? = null
            var mTitleview: TextView? = null
            var mPeriodview: TextView? = null
            // var mDdayview: TextView? = null
            var mCategoryColorview: ImageView? = null

        }
    }


    inner class ListDataAdapter2 : BaseAdapter() {

        var holder: ViewHolder2? = null

        override fun getCount(): Int {
            return mArrayList2!!.size
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
                holder = ViewHolder2()
                convertView = layoutInflater.inflate(R.layout.list_item2, null)
                // holder.mTextview
                holder?.mCategoryview = convertView!!.findViewById<View>(R.id.tv_rv_todo_category2) as TextView
                holder?.mTitleview = convertView!!.findViewById<View>(R.id.tv_rv_todo_title2) as TextView
                holder?.mPeriodview = convertView!!.findViewById<View>(R.id.tv_rv_todo_period2) as TextView
                holder?.mDdayview = convertView!!.findViewById<View>(R.id.tv_rv_d_day3) as TextView
                holder?.mCategoryColorview =
                        convertView!!.findViewById<View>(R.id.iv_rv_todo_category_color2) as ImageView
                convertView.tag = holder
            } else {
                holder = convertView.tag as ViewHolder2
            }

            when (mArrayList.get(position).category) {
                0 -> {
                    holder?.mCategoryview!!.text = "공모전"
                    holder?.mCategoryview!!.setTextColor(ContextCompat.getColor(ctx, R.color.color0))
                    holder?.mCategoryColorview!!.setImageDrawable(
                        ContextCompat.getDrawable(
                            ctx,
                            R.drawable.label_task_blue
                        )
                    )
                }
                1 -> {
                    holder?.mCategoryview!!.text = "대외활동"
                    holder?.mCategoryview!!.setTextColor(ContextCompat.getColor(ctx, R.color.color1))
                    holder?.mCategoryColorview!!.setImageDrawable(
                        ContextCompat.getDrawable(
                            ctx,
                            R.drawable.label_task_skyblue
                        )
                    )
                }
                2 -> {
                    holder?.mCategoryview!!.text = "동아리"
                    holder?.mCategoryview!!.setTextColor(ContextCompat.getColor(ctx, R.color.color2))
                    holder?.mCategoryColorview!!.setImageDrawable(
                        ContextCompat.getDrawable(
                            ctx,
                            R.drawable.label_task_violet
                        )
                    )
                }
                3 -> {
                    holder?.mCategoryview!!.text = "교내활동"
                    holder?.mCategoryview!!.setTextColor(ContextCompat.getColor(ctx, R.color.color3))
                    holder?.mCategoryColorview!!.setImageDrawable(
                        ContextCompat.getDrawable(
                            ctx,
                            R.drawable.label_task_lavender
                        )
                    )
                }
                4 -> {
                    holder?.mCategoryview!!.text = "채용"
                    holder?.mCategoryview!!.setTextColor(ContextCompat.getColor(ctx, R.color.color4))
                    holder?.mCategoryColorview!!.setImageDrawable(
                        ContextCompat.getDrawable(
                            ctx,
                            R.drawable.label_task_coral_red
                        )
                    )
                }
                5 -> {
                    holder?.mCategoryview!!.text = "기타"
                    holder?.mCategoryview!!.setTextColor(ContextCompat.getColor(ctx, R.color.color5))
                    holder?.mCategoryColorview!!.setImageDrawable(
                        ContextCompat.getDrawable(
                            ctx,
                            R.drawable.label_task_pink
                        )
                    )
                }
            }
            holder?.mTitleview!!.text = mArrayList2!!.get(position).eventName

            //계산코드
            holder?.mPeriodview!!.text = (mArrayList2!!.get(position).startDay?.substring(5, 7)).toString() + "." +
                    mArrayList!!.get(position).startDay?.substring(8, 10).toString() + " ~ " +
                    (mArrayList!!.get(position).endDay?.substring(5, 7)).toString() + "." +
                    mArrayList!!.get(position).endDay?.substring(8, 10).toString()

            holder?.mDdayview!!.text = (mArrayList2!!.get(position).dday).toString()

            return convertView
        }

        inner class ViewHolder2 {
            var mCategoryview: TextView? = null
            var mTitleview: TextView? = null
            var mPeriodview: TextView? = null
            var mDdayview: TextView? = null
            var mCategoryColorview: ImageView? = null

        }
    }


}
