package com.sopt.appjam_sggsag.Fragment.Career

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import com.sopt.appjam_sggsag.Adapter.Career.AwardRecyclerViewAdapter
import com.sopt.appjam_sggsag.Adapter.Career.CertificateRecyclerViewAdapter
import com.sopt.appjam_sggsag.Career.LicenseDetail
import com.sopt.appjam_sggsag.Data.Career.CertificateListData
import com.sopt.appjam_sggsag.R
import kotlinx.android.synthetic.main.fragment_career3.*
import org.jetbrains.anko.find
import org.jetbrains.anko.support.v4.startActivity

class Career3Fragment : Fragment() {
    lateinit var certificateRecyclerViewAdapter: CertificateRecyclerViewAdapter
    private var career3Fragment: View? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        career3Fragment = inflater!!.inflate(R.layout.fragment_career3, container, false)
        setBtnOnClickListener()
        // Inflate the layout for this fragment
        return career3Fragment
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

    private fun setBtnOnClickListener(){
        val add_certificate: RelativeLayout = career3Fragment!!.find(R.id.btn_add_certificate)
        add_certificate.setOnClickListener {
            startActivity<LicenseDetail>()
        }

    }
}
