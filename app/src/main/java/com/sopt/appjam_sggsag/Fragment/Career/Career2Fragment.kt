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
import com.sopt.appjam_sggsag.Data.Career.AwardListData
import com.sopt.appjam_sggsag.R
import kotlinx.android.synthetic.main.fragment_career1.*
import org.jetbrains.anko.find
import org.jetbrains.anko.support.v4.startActivity

class Career2Fragment : Fragment() {

    var REQUEST_CODE_OUT_ACTIVITY: Int = 222
    private var career2Fragment: View? = null
    private var titleTxt: String? = null
    private var startDate: String? = null
    private var endDate: String? = null
    private var contentTxt: String? = null
    lateinit var awardRecyclerViewAdapter: AwardRecyclerViewAdapter
    var dataList: ArrayList<AwardListData> = ArrayList()

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

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == REQUEST_CODE_OUT_ACTIVITY) {
            if (resultCode == Activity.RESULT_OK) {

                titleTxt = data!!.getStringExtra("title").toString()
                startDate = data!!.getStringExtra("start_data").toString()
                endDate = data!!.getStringExtra("end_data").toString()
                contentTxt = data!!.getStringExtra("notes").toString()

                dataList.add(AwardListData(titleTxt!!, startDate + " ~ " + endDate, contentTxt!!))

//                careerRecyclerViewAdapter = CareerRecyclerViewAdapter(activity!!, dataList)
                rv_career1_frag_activity_list.adapter = awardRecyclerViewAdapter
                rv_career1_frag_activity_list.layoutManager = LinearLayoutManager(activity)

                invisibleImage()
            }
        }
    }

    private fun setBtnOnClickListener(){
        val add_award: RelativeLayout = career2Fragment!!.find(R.id.btn_add_award)
        add_award.setOnClickListener {
            startActivity<AwardDetail>()
        }

    }

    fun invisibleImage() {
        rl_empty_career.visibility = View.INVISIBLE
    }

}