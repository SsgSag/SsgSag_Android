package com.sopt.appjam_sggsag.Fragment




import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_calendar.*
import org.jetbrains.anko.support.v4.startActivity
import java.util.*
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.graphics.Typeface
import android.text.style.StyleSpan
import android.util.Log
import com.sopt.appjam_sggsag.Adapter.CalendarViewPagerAdapter
import com.sopt.appjam_sggsag.Adapter.CalendarViewPagerAdapter2
import com.sopt.appjam_sggsag.Adapter.TodoListRecyclerViewAdapter
import com.sopt.appjam_sggsag.Data.TodoListData
import com.sopt.appjam_sggsag.Interface.GetYearMonthTab
import com.sopt.appjam_sggsag.R
import com.sopt.appjam_sggsag.ScheduleRegisterActivity
import android.view.animation.AlphaAnimation
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import com.sopt.appjam_sggsag.DB.SharedPreferenceController
import com.sopt.appjam_sggsag.Data.EventList
import com.sopt.appjam_sggsag.MyApplication
import com.sopt.appjam_sggsag.Network.NetworkService
import com.sopt.appjam_sggsag.Post.CalendarData
import com.sopt.appjam_sggsag.Post.PostCalendarResponse
import org.jetbrains.anko.support.v4.toast
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class CalendarFragment : Fragment(), GetYearMonthTab {

    override fun onClick(year: Int, month: Int, day: String, position:Int) {
        if (day != "") {
            val animation = AlphaAnimation(1f, 0f)
            animation.duration = 400
            val animation2 = AlphaAnimation(0f, 1f)
            animation2.duration = 400
/*
            schedule_linear_layout.visibility = (View.VISIBLE)
            schedule_linear_layout.setAnimation(animation);
            vp_frag_calendar_view_pager.visibility = View.GONE
            vp_frag_calendar_view_pager.setAnimation(animation2);
            */
        }
    }

    // var select_year : Int? = null       //RegisterScheduleActivity에서 넘겨받을 날짜 정보(spinner로 부터)
    // var select_month : Int? = null
    // var select_day : Int? = null
    var temp_month = 1;
    var calendar_month = 24;
    var mArrayList: ArrayList<String>? = ArrayList()
    var listServer: ArrayList<CalendarData>? = ArrayList()
    // var todoListListener : Int = 0


    val networkService: NetworkService by lazy {
        MyApplication.instance.networkService
    }

    var scheduleList: ArrayList<EventList> = ArrayList()
    companion object {
        private var instance: CalendarFragment? = null
        @Synchronized
        fun getInstance(year: Int, month: Int, day: Int): CalendarFragment {
            if (instance == null) {
                instance = CalendarFragment().apply {
                    arguments = Bundle().apply {
                        putInt("year", year)
                        putInt("month", month)
                        putInt("day", day)
                    }
                }
            }
            return instance!!
        }
    }

    //RegisterScheduleActivity로 부터 정보 꺼내오기
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        scheduleList = (activity!!.application as MyApplication).eventList1
        //    getCalendarResponse("2018","12")
        getCalendarResponse("2019","01")
        //   getCalendarResponse("2019","02")
//        getCalendarResponse("2019","03")
        /*
        arguments?.let{
            select_year = it.getInt("year")
            select_month = it.getInt("month")
            select_day = it.getInt("day")
        }
        */
    }

    override fun getYearMonthTab(year: String, month: String) {

        val sp: SpannableStringBuilder?
        if (month.length == 1) {
            sp = SpannableStringBuilder(year + ".0" + month)
        } else {
            sp = SpannableStringBuilder(year + "." + month)
        }
        //1월 -> 12월, 12월 -> 1월 고려 안한 코드
        if (temp_month < month.toInt()) {
            calendar_month++
        } else if (temp_month > month.toInt()) {
            calendar_month--
        } else {

        }
        temp_month = month.toInt()
        Log.e("temp_month", temp_month.toString())
        sp.setSpan(StyleSpan(Typeface.BOLD), 4, 7, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        frag_calendar_year_month.setText(sp)


    }

    lateinit var todoListRecyclerViewAdapter: TodoListRecyclerViewAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val calendarFragment: View = inflater!!.inflate(R.layout.fragment_calendar, container, false)

        //calendarFragment.frag_calendar_view.addDecorators(sundayDecorator, onedayDecorator)
        //  setOnClickListener()

        return calendarFragment

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        configureBottomNavigation()
        setOnClickListener()

    }


    private fun setOnClickListener() {
        /*
        frag_calendar_tv_title.setOnClickListener{
            frag_calendar_tv_title.setVisibility(TextView.INVISIBLE);
            rv_frag_calendar_todo_list.setVisibility(TextView.INVISIBLE);
            val params = vp_frag_calendar_view_pager.getLayoutParams() //as LayoutParams
            params.height = (params.height*8*(0.2)).toInt()
            vp_frag_calendar_view_pager.setLayoutParams(params)

        }
*//*
        frag_calendar_before.setOnClickListener {
            calendar_month--
            vp_frag_calendar_view_pager.setCurrentItem(calendar_month, true)
            Log.e("beforeButton", calendar_month.toString())
            //      vp_frag_calendar_view_pager2.setCurrentItem(calendar_month + 1, true)
            //calendar_month++
        }

        frag_calendar_next.setOnClickListener {
            calendar_month++
            vp_frag_calendar_view_pager.setCurrentItem(calendar_month, true)
            Log.e("CalendarLog22", calendar_month.toString())
            Log.e("nextButton", calendar_month.toString())
            //    vp_frag_calendar_view_pager2.setCurrentItem(calendar_month - 1, true)
            //calendar_month--
        }
        */
        frag_calendar_iv_register.setOnClickListener {
            startActivity<ScheduleRegisterActivity>()
            activity!!.finish()

        }



    }




    private fun configureBottomNavigation() {

        vp_frag_calendar_view_pager.adapter = CalendarViewPagerAdapter(childFragmentManager, 50, this) //3개를 고정시키겠다.
        vp_frag_calendar_view_pager.offscreenPageLimit = 0
        vp_frag_calendar_view_pager.setCurrentItem(24, true)

    }


    private fun getCalendarResponse(year:String, month:String) {
        var jsonObject = JSONObject()
        jsonObject.put("year", year)
        jsonObject.put("month", month)
        jsonObject.put("day", "00")
        val gsonObject = JsonParser().parse(jsonObject.toString()) as JsonObject

        val token = SharedPreferenceController.getAuthorization(this.context!!)
        val postCalendarResponse: Call<PostCalendarResponse> = networkService.postCalendarResponse(
            "application/json",
            "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJEb0lUU09QVCIsInVzZXJfaWR4IjoxfQ.5lCvAqnzYP4-2pFx1KTgLVOxYzBQ6ygZvkx5jKCFM08"
            ,gsonObject
        )
        postCalendarResponse.enqueue(object : Callback<PostCalendarResponse> {
            override fun onFailure(call: Call<PostCalendarResponse>, t: Throwable) {
                Log.e("calendar fail", t.toString())
            }

            override fun onResponse(call: Call<PostCalendarResponse>, response: Response<PostCalendarResponse>) {
                if (response.isSuccessful) {
                    toast(response.body()!!.message)
                    response.body()?.status
                    listServer = response.body()?.data
                    for(i in 0..listServer!!.size-1){
                        var startDate = listServer!![i].posterStartDate
                        var endDate = listServer!![i].posterEndDate
                        var startYear = listServer!![i].posterStartDate.substring(0, 4).toInt()
                        var startMonth = listServer!![i].posterStartDate.substring(5, 7).toInt()
                        var startDay = listServer!![i].posterStartDate.substring(8, 10).toInt()
                        var endYear = listServer!![i].posterEndDate.substring(0, 4).toInt()
                        var endMonth = listServer!![i].posterEndDate.substring(5, 7).toInt()
                        var endDay = listServer!![i].posterEndDate.substring(8, 10).toInt()
                        var eventName = listServer!![i].posterName
                        var category = listServer!![i].categoryIdx
                        var dday = listServer!![i].dday
                        if (tempcount==i) {
                            //이게
                            if (startYear == endYear && startMonth == endMonth) {
                                for (i in startDay..endDay) {
                                    scheduleList.add(EventList(startDate, endDate, startYear, startMonth, i, eventName, category, dday))
                                }
                            }
                            else if(startYear == endYear){
                                when(startMonth){
                                    1,3,5,7,8,10,12->{
                                        for(i in startDay..31){
                                            scheduleList.add(EventList(startDate, endDate, startYear, startMonth, i, eventName, category, dday))
                                        }
                                        for(i in 1..endDay){
                                            scheduleList.add(EventList(startDate, endDate, startYear, endMonth, i, eventName, category, dday))
                                        }
                                    }
                                    2->{
                                        for(i in startDay..28){
                                            scheduleList.add(EventList(startDate, endDate, startYear, startMonth, i, eventName, category, dday))
                                        }
                                        for(i in 1..endDay){
                                            scheduleList.add(EventList(startDate, endDate, startYear, endMonth, i, eventName, category, dday))
                                        }
                                    }
                                    else->{
                                        for(i in startDay..30){
                                            scheduleList.add(EventList(startDate, endDate, startYear, startMonth, i, eventName, category, dday))
                                        }
                                        for(i in 1..endDay){
                                            scheduleList.add(EventList(startDate, endDate, startYear, endMonth, i, eventName, category, dday))
                                        }
                                    }

                                }
                            }
                            tempcount++
                        }
                    }
                    Log.e("calendar success", response.body()!!.message)
                }
            }
        })
    }
}

