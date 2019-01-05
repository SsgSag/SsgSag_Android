package com.sopt.appjam_sggsag.Fragment


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import kotlinx.android.synthetic.main.fragment_calendar.*
import kotlinx.android.synthetic.main.fragment_calendar.view.*
import org.jetbrains.anko.support.v4.startActivity
import java.util.*
import android.text.Spannable
import android.text.style.ForegroundColorSpan
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


class CalendarFragment : Fragment(), GetYearMonthTab {

    // var select_year : Int? = null       //RegisterScheduleActivity에서 넘겨받을 날짜 정보(spinner로 부터)
    // var select_month : Int? = null
    // var select_day : Int? = null
    var temp_month=0;
    var calendar_month = 24;
    companion object {
        private var instance : CalendarFragment? = null
        @Synchronized
        fun getInstance(year: Int, month:Int, day: Int ) : CalendarFragment{
            if(instance==null){
                instance = CalendarFragment().apply{
                    arguments = Bundle().apply{
                        putInt("year",year)
                        putInt("month",month)
                        putInt("day",day)
                    }
                }
            }
            return instance!!
        }
    }

    //RegisterScheduleActivity로 부터 정보 꺼내오기
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /*
        arguments?.let{
            select_year = it.getInt("year")
            select_month = it.getInt("month")
            select_day = it.getInt("day")
        }
        */
    }

    override fun getYearMonthTab(year: String, month: String) {

        val sp :SpannableStringBuilder?
        if(month.length==1){
            sp = SpannableStringBuilder(year+".0"+month)
        }
        else {
            sp = SpannableStringBuilder(year + "." + month)
        }
        //1월 -> 12월, 12월 -> 1월 고려 안한 코드
        if(temp_month<month.toInt()){
            calendar_month++
        }
        else if(temp_month>month.toInt()){
            calendar_month--
        }
        else{

        }

        temp_month=month.toInt()
        Log.e("temp_month",temp_month.toString())
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
        setRecyclerView()
        configureBottomNavigation()
        setOnClickListener()

    }


    private fun setOnClickListener(){
        /*
        frag_calendar_tv_title.setOnClickListener{
            frag_calendar_tv_title.setVisibility(TextView.INVISIBLE);
            rv_frag_calendar_todo_list.setVisibility(TextView.INVISIBLE);
            val params = vp_frag_calendar_view_pager.getLayoutParams() //as LayoutParams
            params.height = (params.height*8*(0.2)).toInt()
            vp_frag_calendar_view_pager.setLayoutParams(params)

        }
*/
        frag_calendar_before.setOnClickListener {
            //calendar_month--
            vp_frag_calendar_view_pager.setCurrentItem(calendar_month+1,true)
            vp_frag_calendar_view_pager2.setCurrentItem(calendar_month+1,true)
            calendar_month++
        }

        frag_calendar_next.setOnClickListener {
            //  calendar_month++
            vp_frag_calendar_view_pager.setCurrentItem(calendar_month-1,true)
            vp_frag_calendar_view_pager2.setCurrentItem(calendar_month-1,true)
            calendar_month--
        }
        frag_calendar_iv_register.setOnClickListener {

            startActivity<ScheduleRegisterActivity>()
            activity!!.finish()

        }

    }


    private fun setRecyclerView(){
        //임시 데이터
        var dataList:ArrayList<TodoListData> = ArrayList()
        dataList.add(TodoListData("할 일1","D-3"))
        dataList.add(TodoListData("할 일2", "D-2"))

        todoListRecyclerViewAdapter = TodoListRecyclerViewAdapter(activity!!,dataList)
        rv_frag_calendar_todo_list.adapter = todoListRecyclerViewAdapter
        rv_frag_calendar_todo_list.layoutManager = LinearLayoutManager(activity)
    }


    private fun configureBottomNavigation() {

        vp_frag_calendar_view_pager.adapter = CalendarViewPagerAdapter(childFragmentManager, 50,this) //3개를 고정시키겠다.
        vp_frag_calendar_view_pager2.adapter = CalendarViewPagerAdapter2(childFragmentManager, 50, this)
        vp_frag_calendar_view_pager.offscreenPageLimit = 0
        vp_frag_calendar_view_pager2.offscreenPageLimit = 0
        vp_frag_calendar_view_pager.setCurrentItem(24,true)
        vp_frag_calendar_view_pager2.setCurrentItem(24,true)

        //frag_calendar_year_month.setText(year.toString())
        //오빠 저 그럼 오빠가 해결해줄 수 있는 질문 하나 더 있어요 ㅎㅅㅎ
        // ViewPager와 Tablayout을 엮어줍니다!
        //tl_top_frag_cal_top_menu.setupWithViewPager(vp_frag_calendar_view_pager)
        //TabLayout에 붙일 layout을 찾아준 다음

        //  val bottomNaviLayout: View = this.layoutInflater.inflate(R.layout.fragment_top_navi_calendar, null, false)
        //탭 하나하나 TabLayout에 연결시켜줍니다.

        //tl_top_frag_cal_top_menu.getTabAt(0)!!.customView = bottomNaviLayout.findViewById(R.id.btn_navi_calendar_before) as RelativeLayout
        //tl_top_navi_act_top_menu.getTabAt(1)!!.customView = bottomNaviLayout.findViewById(R.id.btn_navi_calendar_now) as RelativeLayout
        //tl_top_navi_act_top_menu.getTabAt(2)!!.customView = bottomNaviLayout.findViewById(R.id.btn_navi_calendar_next) as RelativeLayout

    }
/*
    companion object {
        private var instance: CalendarFragment? = null
        @Synchronized
        fun getInstance(): CalendarFragment {
            if (instance == null) {
                instance = CalendarFragment()
            }
            return instance!!
        }
    }

   */
/*
    fun setOnClickListener(calendarFragment: View){
        calendarFragment.frag_calendar_view.setOnDateChangedListener { widget, date, selected ->

            var onedaydecorator2 = OneDayDecorator(date)
            //여기에 그 투두리스트 날짜에 맞게 나오는 코드
            var year = date.year
            var month = date.month+1
            var day = date.day
            var today:String = year.toString()+"년"+month.toString()+"월"+day.toString()+"일"
            Toast.makeText(context, today, Toast.LENGTH_SHORT).show()
            Log.e("log test1", date.toString())
            widget.addDecorator(onedaydecorator2)

        }
    }

*/
}