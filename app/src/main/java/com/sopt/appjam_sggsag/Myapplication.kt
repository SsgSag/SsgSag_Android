package com.sopt.appjam_sggsag

import android.app.Application
import android.content.Context
import android.util.DisplayMetrics
import android.view.WindowManager
import com.sopt.appjam_sggsag.Data.CalendarDateData
import com.sopt.appjam_sggsag.Data.EventList
import com.sopt.appjam_sggsag.Network.ApplicationController
import com.sopt.appjam_sggsag.Network.NetworkService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MyApplication : Application() {
    public var screen_height:Int? = 0
    override fun onCreate() {
        super.onCreate()
        setData()
        //screen_height = getScreenHeightInDPs(this) 스크린 높이
    }

    public var dataList1: ArrayList<CalendarDateData> = ArrayList()
    public var eventList1: ArrayList<EventList> = ArrayList()
    public var flag_main_act : Int = 0
    public fun setData() {
        dataList1.add(CalendarDateData(1, "1","red"))
        dataList1.add(CalendarDateData(2, "2",""))
        dataList1.add(CalendarDateData(3, "3",""))
        dataList1.add(CalendarDateData(4, "4",""))
        dataList1.add(CalendarDateData(5, "5",""))
        dataList1.add(CalendarDateData(6, "6",""))
        dataList1.add(CalendarDateData(7, "7","blue"))
        dataList1.add(CalendarDateData(8, "8","red"))
        dataList1.add(CalendarDateData(9, "9",""))
        dataList1.add(CalendarDateData(10, "10",""))
        dataList1.add(CalendarDateData(11, "11",""))
        dataList1.add(CalendarDateData(12, "12",""))
        dataList1.add(CalendarDateData(13, "13",""))
        dataList1.add(CalendarDateData(14, "14","blue"))
        dataList1.add(CalendarDateData(15, "15","red"))
        dataList1.add(CalendarDateData(16, "16",""))
        dataList1.add(CalendarDateData(17, "17",""))
        dataList1.add(CalendarDateData(18, "18",""))
        dataList1.add(CalendarDateData(19, "19",""))
        dataList1.add(CalendarDateData(20, "20",""))
        dataList1.add(CalendarDateData(21, "21","blue"))
        dataList1.add(CalendarDateData(22, "22","red"))
        dataList1.add(CalendarDateData(23, "23",""))
        dataList1.add(CalendarDateData(24, "24",""))
        dataList1.add(CalendarDateData(25, "25",""))
        dataList1.add(CalendarDateData(26, "26",""))
        dataList1.add(CalendarDateData(27, "27",""))
        dataList1.add(CalendarDateData(28, "28","blue"))
        dataList1.add(CalendarDateData(29, "29","red"))
        dataList1.add(CalendarDateData(30, "30",""))
        dataList1.add(CalendarDateData(31, "31",""))
        dataList1.add(CalendarDateData(32, "32",""))
        dataList1.add(CalendarDateData(33, "33",""))
        dataList1.add(CalendarDateData(34, "34",""))
        dataList1.add(CalendarDateData(35, "35","blue"))
        dataList1.add(CalendarDateData(36, "36","red"))
        dataList1.add(CalendarDateData(37, "37",""))
        dataList1.add(CalendarDateData(38, "38",""))
        dataList1.add(CalendarDateData(39, "39",""))
        dataList1.add(CalendarDateData(40, "40",""))
        dataList1.add(CalendarDateData(41, "41",""))
        dataList1.add(CalendarDateData(42, "42","blue"))
        eventList1.add(EventList(2019,1,8,"대외활동",1))
        eventList1.add(EventList(2019,1,6,"대외활동",1))
        eventList1.add(EventList(2019,1,7,"대외활동",1))
        eventList1.add(EventList(2019,1,7,"동아리",2))
        eventList1.add(EventList(2019,1,8,"동아리",2))
        eventList1.add(EventList(2019,1,9,"동아리",2))
        eventList1.add(EventList(2019,1,9,"대외활동2",1))
        eventList1.add(EventList(2019,1,10,"대외활동2",1))
        eventList1.add(EventList(2019,1,11,"대외활동2",1))
        eventList1.add(EventList(2019,1,12,"대외활동2",1))
        eventList1.add(EventList(2019,1,13,"대외활동2",1))
        eventList1.add(EventList(2019,1,8,"채용",4))
        eventList1.add(EventList(2019,1,9,"채용",4))
        eventList1.add(EventList(2019,1,10,"채용",4))
        eventList1.add(EventList(2019,1,11,"채용",4))
        eventList1.add(EventList(2019,1,12,"교내공지",3))
        eventList1.add(EventList(2019,1,13,"교내공지",3))
        eventList1.add(EventList(2019,1,13,"동아리2",2))
        eventList1.add(EventList(2019,1,14,"동아리2",2))
        eventList1.add(EventList(2019,2,13,"동아리2",2))
        eventList1.add(EventList(2019,2,14,"동아리2",2))


    }
    /*
    fun getScreenHeightInDPs(context: Context): Int {
        val dm = DisplayMetrics()
        val windowManager = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        windowManager.defaultDisplay.getMetrics(dm)

        return Math.round(dm.heightPixels / dm.density)
    }
    */
    private val baseURL = "http://54.180.79.158:8080/"
    lateinit var networkService: NetworkService
    companion object {
        lateinit var instance: MyApplication
    }
    override fun onCreate() {
        super.onCreate()
        instance = this
        buildNetWork()
    }
    fun buildNetWork() {
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(baseURL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        networkService = retrofit.create(NetworkService::class.java)
    }

}
