package com.sopt.appjam_sggsag.Adapter

import android.content.Context
import android.graphics.Color
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import com.sopt.appjam_sggsag.Data.CalendarDateData
import com.sopt.appjam_sggsag.Data.EventList
import com.sopt.appjam_sggsag.Data.EventNameList
import com.sopt.appjam_sggsag.R
import org.jetbrains.anko.toast
import java.util.*

var year: Int = 2019
var toDay: Int = 1   //아니 이게 사실은 달 받아오는 거임.

class CalendarRecyclerAdapter(
    val ctx: Context,
    val dataList: ArrayList<CalendarDateData>,
    val scheduleList: ArrayList<EventList>,
    val month: Int
) :
    RecyclerView.Adapter<CalendarRecyclerAdapter.Holder>() {

    var eventName: String? = null      //scheduleList에 있는 event 종류 저장
    val eventNameList: ArrayList<EventNameList> = ArrayList()   //우선순위 순 이벤트 저장
    internal var startDay: Int = 0  //이번달의 시작요일
    //  internal var toDay: Int = 0   //오늘
    internal var lastDay: Int = 0  //마지막 요일
    var arr = Array<IntArray>(42, { IntArray(5) })
    val params =
        LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT)
    val params2 =
        LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT)
    var flag = 0



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view: View = LayoutInflater.from(ctx).inflate(R.layout.rv_item_calendar, parent, false)
        eventNameList.clear()   //시작시 우선순위 갱신하기 위해 eventNameList 클리어
        for (i in 0..41) {
            for (j in 0..4) {
                arr[i][j] = 9999
            }
        }
        saveEventName()     //scheduleList에 있는 이벤트 종류 저장
        sorting()
        //scheduleList 기반 eventNameList에 기간순으로 이벤트 저장. ex) 가장 긴 일정 : 인덱스0을 가짐.
        setDay(month)       //날짜 셋팅, arr 배열 채우기
        params.setMargins(0, 0, 10, 5)   //두번째날부터 쓸 마진, 첫번째날은 있는 그대로 ㅇㅋㅇㅋ
        params2.setMargins(0, 0, 0, 5)
        return Holder(view)
    }

    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(holder: Holder, position: Int) {

        holder.numberView1.text = dataList[position].day

        if (dataList[position].color == "blue") {
            holder.numberView1.setTextColor(Color.BLUE)
        } else if (dataList[position].color == "red")
            holder.numberView1.setTextColor(Color.RED)
        holder.numberView2.text = ""
        holder.numberView3.text = ""
        holder.numberView4.text = ""
        holder.numberView5.text = ""
        holder.numberView2.layoutParams = params2
        holder.numberView3.layoutParams = params2
        holder.numberView4.layoutParams = params2
        holder.numberView5.layoutParams = params2
        // holder.numberView2.setTextSize(1.0f)
        // holder.numberView3.setTextSize(1.0f)
        // holder.numberView4.setTextSize(1.0f)
        //  holder.numberView5.setTextSize(1.0f)
        //   Log.e("arr[position]",arr[position][1])
        if (arr[position][1] != 9999) {
            if(eventNameList[arr[position][1]].minDay.toString()== dataList[position].day || position%7==0){
                holder.numberView2.text = " "+eventNameList[arr[position][1]].eventName
                // holder.numberView2.setTextSize(1.0f)
            }
            if(eventNameList[arr[position][1]].maxDay.toString()== dataList[position].day){
                holder.numberView2.layoutParams = params
                // holder.numberView2.setTextSize(1.0f)
            }
            when (eventNameList[arr[position][1]].category) {
                0 -> {
                    holder.numberView2.setBackgroundColor(ContextCompat.getColor(ctx, R.color.color0))
                }
                1 -> {
                    holder.numberView2.setBackgroundColor(ContextCompat.getColor(ctx, R.color.color1))
                }
                2 -> {
                    holder.numberView2.setBackgroundColor(ContextCompat.getColor(ctx, R.color.color2))
                }
                3 -> {
                    holder.numberView2.setBackgroundColor(ContextCompat.getColor(ctx, R.color.color3))
                }
                4 -> {
                    holder.numberView2.setBackgroundColor(ContextCompat.getColor(ctx, R.color.color4))
                }
                5 -> {
                    holder.numberView2.setBackgroundColor(ContextCompat.getColor(ctx, R.color.color4))
                }
                else -> {
                    holder.numberView2.setBackgroundColor(ContextCompat.getColor(ctx, R.color.color5))
                }
            }
            //setColor(holder.numberView2,position)
        }
        if (arr[position][2] != 9999) {
            if(eventNameList[arr[position][2]].minDay.toString()== dataList[position].day || position%7==0) {
                holder.numberView3.text = " "+eventNameList[arr[position][2]].eventName
                //      holder.numberView3.setTextSize(1.0f)
            }
            if(eventNameList[arr[position][2]].maxDay.toString()== dataList[position].day){
                holder.numberView3.layoutParams = params
                //     holder.numberView3.setTextSize(1.0f)
            }
            when (eventNameList[arr[position][2]].category) {
                0 -> {
                    holder.numberView3.setBackgroundColor(ContextCompat.getColor(ctx, R.color.color0))
                }
                1 -> {
                    holder.numberView3.setBackgroundColor(ContextCompat.getColor(ctx, R.color.color1))
                }
                2 -> {
                    holder.numberView3.setBackgroundColor(ContextCompat.getColor(ctx, R.color.color2))
                }
                3 -> {
                    holder.numberView3.setBackgroundColor(ContextCompat.getColor(ctx, R.color.color3))
                }
                4 -> {
                    holder.numberView3.setBackgroundColor(ContextCompat.getColor(ctx, R.color.color4))
                }
                5 -> {
                    holder.numberView3.setBackgroundColor(ContextCompat.getColor(ctx, R.color.color4))
                }
                else -> {
                    holder.numberView3.setBackgroundColor(ContextCompat.getColor(ctx, R.color.color5))
                }
            }
            //setColor(holder.numberView3,position)
        }
        if (arr[position][3] != 9999) {
            if(eventNameList[arr[position][3]].minDay.toString()== dataList[position].day || position%7==0) {
                holder.numberView4.text = " "+eventNameList[arr[position][3]].eventName
                //   holder.numberView4.setTextSize(1.0f)
            }
            if(eventNameList[arr[position][3]].maxDay.toString()== dataList[position].day){
                holder.numberView4.layoutParams = params
                //   holder.numberView4.setTextSize(1.0f)
            }
            when (eventNameList[arr[position][3]].category) {
                0 -> {
                    holder.numberView4.setBackgroundColor(ContextCompat.getColor(ctx, R.color.color0))
                }
                1 -> {
                    holder.numberView4.setBackgroundColor(ContextCompat.getColor(ctx, R.color.color1))
                }
                2 -> {
                    holder.numberView4.setBackgroundColor(ContextCompat.getColor(ctx, R.color.color2))
                }
                3 -> {
                    holder.numberView4.setBackgroundColor(ContextCompat.getColor(ctx, R.color.color3))
                }
                4 -> {
                    holder.numberView4.setBackgroundColor(ContextCompat.getColor(ctx, R.color.color4))
                }
                5 -> {
                    holder.numberView4.setBackgroundColor(ContextCompat.getColor(ctx, R.color.color4))
                }
                else -> {
                    holder.numberView4.setBackgroundColor(ContextCompat.getColor(ctx, R.color.color5))
                }
            }
            //setColor(holder.numberView4,position)

        }
        if (arr[position][4] != 9999) {
            if(eventNameList[arr[position][4]].minDay.toString()== dataList[position].day || position%7==0) {
                holder.numberView5.text = " "+eventNameList[arr[position][4]].eventName
                //   holder.numberView5.setTextSize(1.0f)
            }
            if(eventNameList[arr[position][4]].maxDay.toString()== dataList[position].day){
                holder.numberView5.layoutParams = params
                //   holder.numberView5.setTextSize(1.0f)
            }
            when (eventNameList[arr[position][4]].category) {
                0 -> {
                    holder.numberView5.setBackgroundColor(ContextCompat.getColor(ctx, R.color.color0))
                }
                1 -> {
                    holder.numberView5.setBackgroundColor(ContextCompat.getColor(ctx, R.color.color1))
                }
                2 -> {
                    holder.numberView5.setBackgroundColor(ContextCompat.getColor(ctx, R.color.color2))
                }
                3 -> {
                    holder.numberView5.setBackgroundColor(ContextCompat.getColor(ctx, R.color.color3))
                }
                4 -> {
                    holder.numberView5.setBackgroundColor(ContextCompat.getColor(ctx, R.color.color4))
                }
                5 -> {
                    holder.numberView5.setBackgroundColor(ContextCompat.getColor(ctx, R.color.color4))
                }
                else -> {
                    holder.numberView5.setBackgroundColor(ContextCompat.getColor(ctx, R.color.color5))
                }
            }
            //setColor(holder.numberView5,position)
        }


        holder.oneDay.setOnClickListener {
            //토스트뜨고
            //
            holder.numberView1
            ctx.toast(holder.numberView1.text)
            //하트 상태 바꾸고
            /*
            if(dataList[position].isLike==true){
                dataList[position].isLike=false
                holder.heartImage.visibility = View.GONE
            }
            else{
                dataList[position].isLike=true
                holder.heartImage.visibility = View.VISIBLE
            }
            */

        }

        //holder.heartImage.image = dataList[position].isLike.image
        /*
        if(dataList[position].isLike==false){
            holder.heartImage.visibility = View.GONE
        }
        */
    }


    private fun getDay(month: Int) {
        val iCal = Calendar.getInstance()
        year = iCal.get(Calendar.YEAR)

        toDay = iCal.get(Calendar.MONTH)
        iCal.set(Calendar.MONTH, (toDay + month))
        iCal.set(Calendar.DATE, 1)      //오늘을 1일이라고 설정.
        startDay = iCal.get(Calendar.DAY_OF_WEEK) - Calendar.SUNDAY
        iCal.add(Calendar.MONTH, 1)
        iCal.add(Calendar.DATE, -1)
        lastDay = iCal.get(Calendar.DAY_OF_MONTH)
    }

    private fun setDay(month: Int) {
        getDay(month)
        var day_count = 0

        for (i in 0 until startDay) {
            dataList[i].day = ""
            arr[i][0] = 0
        }
        for (i in 0..lastDay) {
            day_count++
            dataList[startDay + i].day = day_count.toString()
            arr[startDay + i][0] = day_count
            Log.e(arr[i][0].toString() + "arr[i][0]", arr[i][0].toString())
        }

        for (i in lastDay..(dataList.size - startDay - 1)) {
            dataList[startDay + i].day = ""
            arr[startDay + i][0] = 0
        }

        var count = 0
        Log.e("setday 이벤트네임리스트 크기", eventNameList.size.toString())
        for (i in 0..eventNameList.size - 1) {
            for (h in 0..41) {
                if (arr[h][0] == eventNameList[i].minDay) {
                    for (k in 1..4) {
                        count = 0
                        Log.e("maxDay-minDay+1", eventNameList[i].count.toString())
                        for (check in 0..(eventNameList[i].count - 1)) {
                            if (arr[h + check][k] == 9999) {
                                count++
                            }
                        }
                        if (count == eventNameList[i].count) {
                            for (check in 0..(eventNameList[i].count - 1)) {
                                arr[h + check][k] = i
                            }

                            Log.e("whynot", count.toString())
                            break
                        }
                    }
                    //break 필요
                }

            }

        }


    }



    private fun saveEventName() {

        for (i in 0..scheduleList.size - 1) {
            eventName = scheduleList[i].eventName
            if ((year == scheduleList[i].year) && ((month + 1) == scheduleList[i].month)) {
                if (eventNameList.size != 0) {
                    //현재 탐색 중인 eventName이 eventNameList에 있는지 확인
                    var alreadyExist = 0
                    for (j in 0..eventNameList.size - 1) {
                        if (eventName == eventNameList[j].eventName) {
                            eventNameList[j].count++
                            if (scheduleList[i].day < eventNameList[j].minDay)
                                eventNameList[j].minDay = scheduleList[i].day
                            if (scheduleList[i].day > eventNameList[j].maxDay)
                                eventNameList[j].maxDay = scheduleList[i].day
                            //min max day 저장 가능.
                            alreadyExist++
                        }
                    }

                    if (alreadyExist == 0) {
                        eventNameList.add(EventNameList(eventName!!, scheduleList[i].category, 1, scheduleList[i].day,scheduleList[i].day))
                    } else {
                        alreadyExist = 0
                    }
                } else {
                    eventNameList.add(EventNameList(eventName!!, scheduleList[i].category, 1, scheduleList[i].day,scheduleList[i].day))
                }
            }

        }
    }

    private fun sorting() {
        var maxIndex = 9999;
        var temp: EventNameList? = null
        for (i in 0..eventNameList.size - 1) {
            maxIndex = i
            for (j in i + 1..eventNameList.size - 1) {
                if (eventNameList[j].count > eventNameList[maxIndex].count)
                    maxIndex = j    //
            }
            temp = eventNameList[i]
            eventNameList[i] = eventNameList[maxIndex]
            eventNameList[maxIndex] = temp
        }
    }

    private fun setColor(tv: TextView, position:Int){
        Log.e("[position][i]",arr[position][2].toString())//eventNameList[arr[position][2]].eventName)
        //var category = eventNameList[arr[position][1]].category
        /*
        when (eventNameList[arr[position][1]].category) {

            0 -> {
               tv.setBackgroundColor(ContextCompat.getColor(ctx, R.color.color0))
            }
            1 -> {
                tv.setBackgroundColor(ContextCompat.getColor(ctx, R.color.color1))
            }
            2 -> {
                tv.setBackgroundColor(ContextCompat.getColor(ctx, R.color.color2))
            }
            3 -> {
                tv.setBackgroundColor(ContextCompat.getColor(ctx, R.color.color3))
            }
            4 -> {
                tv.setBackgroundColor(ContextCompat.getColor(ctx, R.color.color4))
            }
            5 -> {
                tv.setBackgroundColor(ContextCompat.getColor(ctx, R.color.color4))
            }

            else -> {
                tv.setTextColor(ContextCompat.getColor(ctx, R.color.color5))
            }

        }
        */

    }

    private fun changeLayout(){
        //fragment_calendar의 schedule_linear_layout를 setVisible을 visible로 바꾼다.

    }

    inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val numberView1: TextView = itemView.findViewById(R.id.numbering1) as TextView
        val numberView2: TextView = itemView.findViewById(R.id.numbering2) as TextView
        val numberView3: TextView = itemView.findViewById(R.id.numbering3) as TextView
        val numberView4: TextView = itemView.findViewById(R.id.numbering4) as TextView
        val numberView5: TextView = itemView.findViewById(R.id.numbering5) as TextView
        val oneDay: LinearLayout = itemView.findViewById(R.id.rv_calendar_linear) as LinearLayout
    }

}