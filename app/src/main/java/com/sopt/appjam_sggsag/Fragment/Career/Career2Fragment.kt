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
import com.sopt.appjam_sggsag.Career.AwardDetail
import com.sopt.appjam_sggsag.Career.LicenseDetail
import com.sopt.appjam_sggsag.Data.Career.AwardListData
import com.sopt.appjam_sggsag.R
import kotlinx.android.synthetic.main.fragment_career1.*
import kotlinx.android.synthetic.main.fragment_career2.*
import org.jetbrains.anko.find
import org.jetbrains.anko.support.v4.startActivity
import org.jetbrains.anko.support.v4.startActivityForResult

class Career2Fragment : Fragment() {

    var REQUEST_CODE_OUT_ACTIVITY: Int = 222
    private var career2Fragment: View? = null
    private var titleTxt: String? = null
    private var startDate: String? = null
    private var contentTxt: String? = null
    lateinit var awardRecyclerViewAdapter: AwardRecyclerViewAdapter
    var dataList: ArrayList<AwardListData> = ArrayList()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        career2Fragment = inflater!!.inflate(R.layout.fragment_career2, container, false)
        setBtnOnClickListener()
        // Inflate the layout for this fragment
        return career2Fragment
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        awardRecyclerViewAdapter = AwardRecyclerViewAdapter(activity!!, dataList)

        if(awardRecyclerViewAdapter.dataList.size==0)
            rl_empty_award.visibility=View.VISIBLE
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == REQUEST_CODE_OUT_ACTIVITY) {
            if (resultCode == Activity.RESULT_OK) {

                titleTxt = data!!.getStringExtra("title").toString()
                startDate = data!!.getStringExtra("start_data").toString()
                contentTxt = data!!.getStringExtra("notes").toString()

                dataList.add(AwardListData(titleTxt!!, startDate!!, contentTxt!!))

//                careerRecyclerViewAdapter = CareerRecyclerViewAdapter(activity!!, dataList)
                rv_career2_frag_award_list.adapter = awardRecyclerViewAdapter
                rv_career2_frag_award_list.layoutManager = LinearLayoutManager(activity)

                invisibleImage()
            }
        }
    }

    private fun setBtnOnClickListener(){
        val add_award: RelativeLayout = career2Fragment!!.find(R.id.btn_add_award)
        add_award.setOnClickListener {
            startActivityForResult<AwardDetail>(REQUEST_CODE_OUT_ACTIVITY)
        }

    }

    fun invisibleImage() {
        rl_empty_award.visibility = View.INVISIBLE
    }

}