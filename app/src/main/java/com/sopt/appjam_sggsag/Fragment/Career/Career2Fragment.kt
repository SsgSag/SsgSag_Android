package com.sopt.appjam_sggsag.Fragment.Career

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import com.sopt.appjam_sggsag.Adapter.Career.AwardRecyclerViewAdapter
import com.sopt.appjam_sggsag.Career.AwardDetail
import com.sopt.appjam_sggsag.Data.Career.AwardListData
import com.sopt.appjam_sggsag.R
import kotlinx.android.synthetic.main.fragment_career2.*
import org.jetbrains.anko.find
import org.jetbrains.anko.support.v4.startActivity

class Career2Fragment : Fragment() {


    lateinit var awardRecyclerViewAdapter: AwardRecyclerViewAdapter
    private var career2Fragment: View? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        career2Fragment = inflater!!.inflate(R.layout.fragment_career2, container, false)
        setBtnOnClickListener()
        // Inflate the layout for this fragment
        return career2Fragment
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setRecyclerView()
    }

    private fun setRecyclerView(){
        var dataList: ArrayList<AwardListData> = ArrayList()
        dataList.add(AwardListData("네이버 그린닷 공모전", "2018.01.06", "대상"))
        awardRecyclerViewAdapter = AwardRecyclerViewAdapter(activity!!, dataList)
        rv_career2_frag_award_list.adapter = awardRecyclerViewAdapter
        rv_career2_frag_award_list.layoutManager = LinearLayoutManager(activity)
    }

    private fun setBtnOnClickListener(){
        val add_award: RelativeLayout = career2Fragment!!.find(R.id.btn_add_award)
        add_award.setOnClickListener {
            startActivity<AwardDetail>()
        }

    }

}