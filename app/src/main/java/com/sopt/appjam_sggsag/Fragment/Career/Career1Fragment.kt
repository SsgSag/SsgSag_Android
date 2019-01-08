package com.sopt.appjam_sggsag.Fragment.Career

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import com.sopt.appjam_sggsag.Adapter.Career.CareerRecyclerViewAdapter
import com.sopt.appjam_sggsag.Career.OutActivityDetail
import com.sopt.appjam_sggsag.Data.Career.CareerListData
import com.sopt.appjam_sggsag.R
import kotlinx.android.synthetic.main.activity_out_detail.*
import kotlinx.android.synthetic.main.fragment_career1.*
import org.jetbrains.anko.find
import org.jetbrains.anko.support.v4.startActivity

class Career1Fragment : Fragment() {
    private var career1Fragment: View? = null
    lateinit var careerRecyclerViewAdapter: CareerRecyclerViewAdapter

    private var titleTxt : String? = null
    private var activityDate : String? = null
    private var contentTxt : String? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        arguments?.let{
            titleTxt = it.getString("title")
            activityDate = it.getString("date")
            contentTxt = it.getString("content")
        }

    }


    companion object {
        private var instance: Career1Fragment? = null
        @Synchronized
        fun getInstance(titleTxt: String, activityDate: String, contentTxt: String): Career1Fragment{
            if(instance==null){
                instance = Career1Fragment().apply{
                    arguments = Bundle().apply{
                        putString("title", titleTxt)
                        putString("date", activityDate)
                        putString("content", contentTxt)
                    }
                }
            }
            return instance!!
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        career1Fragment = inflater!!.inflate(R.layout.fragment_career1, container, false)
        setBtnOnClickListener()

        return career1Fragment
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setRecyclerView()


        val position: Int = careerRecyclerViewAdapter.itemCount
        careerRecyclerViewAdapter.dataList.add(CareerListData(titleTxt, activityDate, contentTxt))
        careerRecyclerViewAdapter.notifyItemInserted(position)


    }


    private fun setRecyclerView(){
        var dataList: ArrayList<CareerListData> = ArrayList()
        dataList.add(CareerListData("안녕하세요", "2018.10", "이런 활동을 했습니다"))

        careerRecyclerViewAdapter = CareerRecyclerViewAdapter(activity!!, dataList)
        rv_career1_frag_activity_list.adapter = careerRecyclerViewAdapter
        rv_career1_frag_activity_list.layoutManager = LinearLayoutManager(activity)

    }

    private fun setBtnOnClickListener(){
        val add_activity: RelativeLayout = career1Fragment!!.find(R.id.btn_add_activity)
        add_activity.setOnClickListener {
            startActivity<OutActivityDetail>()
        }

    }




}