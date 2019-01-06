package com.sopt.appjam_sggsag.Fragment.Career

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sopt.appjam_sggsag.Adapter.Career.AwardRecyclerViewAdapter
import com.sopt.appjam_sggsag.Adapter.Career.CertificateRecyclerViewAdapter
import com.sopt.appjam_sggsag.Data.Career.CertificateListData
import com.sopt.appjam_sggsag.R
import kotlinx.android.synthetic.main.fragment_career3.*

class Career3Fragment : Fragment() {
    lateinit var certificateRecyclerViewAdapter: CertificateRecyclerViewAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_career3, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setRecyclerView()
    }

    private fun setRecyclerView(){
        var dataList: ArrayList<CertificateListData> = ArrayList()
        dataList.add(CertificateListData("토익", "2018.01.06", "912점"))

        certificateRecyclerViewAdapter = CertificateRecyclerViewAdapter(activity!!, dataList)
        rv_career3_frag_certificate_list.adapter = certificateRecyclerViewAdapter
        rv_career3_frag_certificate_list.layoutManager = LinearLayoutManager(activity)

    }
}
