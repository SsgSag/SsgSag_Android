package com.sopt.appjam_sggsag.Fragment.Career

import android.app.Activity
import android.content.Intent
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
import com.sopt.appjam_sggsag.Data.Career.AwardListData
import com.sopt.appjam_sggsag.Data.Career.CertificateListData
import com.sopt.appjam_sggsag.R
import kotlinx.android.synthetic.main.fragment_career3.*
import org.jetbrains.anko.find
import org.jetbrains.anko.support.v4.startActivity
import org.jetbrains.anko.support.v4.startActivityForResult

class Career3Fragment : Fragment() {
    var REQUEST_CODE_OUT_ACTIVITY: Int = 333
    lateinit var certificateRecyclerViewAdapter: CertificateRecyclerViewAdapter
    private var career3Fragment: View? = null
    private var titleTxt: String? = null
    private var date: String? = null
    private var contentTxt: String? = null
    var dataList: ArrayList<CertificateListData> = ArrayList()




    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        career3Fragment = inflater!!.inflate(R.layout.fragment_career3, container, false)
        setBtnOnClickListener()
        // Inflate the layout for this fragment
        return career3Fragment
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        certificateRecyclerViewAdapter = CertificateRecyclerViewAdapter(activity!!, dataList)

        if(certificateRecyclerViewAdapter.dataList.size==0)
            rl_empty_license.visibility=View.VISIBLE
    }



    private fun setBtnOnClickListener(){
        val add_certificate: RelativeLayout = career3Fragment!!.find(R.id.btn_add_certificate)
        add_certificate.setOnClickListener {
            startActivityForResult<LicenseDetail>(REQUEST_CODE_OUT_ACTIVITY)
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE_OUT_ACTIVITY) {
            if (resultCode == Activity.RESULT_OK){
                titleTxt = data!!.getStringExtra("title").toString()
                contentTxt = data!!.getStringExtra("note").toString()
                date = data!!.getStringExtra("date").toString()

                dataList.add(CertificateListData(titleTxt!!, date!!, contentTxt!!))

                rv_career3_frag_certificate_list.adapter = certificateRecyclerViewAdapter
                rv_career3_frag_certificate_list.layoutManager = LinearLayoutManager(activity)

                invisibleImage()
            }
        }
    }

    fun invisibleImage() {
        rl_empty_license.visibility = View.INVISIBLE
    }
}
