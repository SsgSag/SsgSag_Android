package com.sopt.appjam_sggsag.Adapter

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.bumptech.glide.Glide
import com.sopt.appjam_sggsag.R
import com.sopt.appjam_sggsag.Spot
import org.jetbrains.anko.find
import org.w3c.dom.Text

class CardStackAdapter(
    private var spots: List<Spot> = emptyList()
) : RecyclerView.Adapter<CardStackAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
//        getPosterResponse()
        return ViewHolder(inflater.inflate(R.layout.item_spot, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val spot = spots[position]
        holder.name.text = "${spot.name}"
        holder.hash.text = spot.category
        holder.start_date.text=spot.start_date
        holder.end_date.text=spot.end_date

        Glide.with(holder.image)
            .load(spot.url)
            .into(holder.image)
        holder.title_term_4.text ="서류접수 마감"
        holder.summary_4.text="언제나 안심 <에스원> 에서 모두가 안심할 수 있는 첨단 미래를 만들기 위한 <2019 에스원 아이디어 공모전>을 개최합니다."
        holder.target_4.text="국내 거주 대학(원)생 또는 일반인\n팀 지원 시 3인 이내 구성\n"
        holder.date_4.text="2018-01-08"
        holder.benefit_4.text="대상(1명/팀): 500만원 / 최우수상(2명/팀): 각 300만원\n우수상(3명/팀): 각 100만원 / 장려상(4명/팀): 각 50만원\n"


        var widthOfCard = holder.image.width
        var xAtDown: Float
        var xAtUp: Float

        holder.itemView.setOnTouchListener { v, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    xAtDown = event.getX()
                    return@setOnTouchListener true
                }
                MotionEvent.ACTION_UP -> {//클릭
                    xAtUp = event.getX()
                    if (xAtUp <= widthOfCard / 2) {//card의 왼쪽 클릭
                        holder.progress_bar_left.visibility = View.VISIBLE
                        holder.progress_bar_right.visibility = View.INVISIBLE
                        holder.detail_view.visibility = View.INVISIBLE
                        Log.e("왼쪽", "클릭")
                    } else {//card의 오른쪽 클릭
                        holder.progress_bar_left.visibility = View.INVISIBLE
                        holder.progress_bar_right.visibility = View.VISIBLE
                        holder.detail_view.visibility = View.VISIBLE
                        Log.e("오른쪽", "클릭")
                    }
                    return@setOnTouchListener true
                }
                else -> {
                    return@setOnTouchListener false
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return spots.size
    }

    fun setSpots(spots: List<Spot>) {
        this.spots = spots
    }

    fun getSpots(): List<Spot> {
        return spots
    }

    var poster_index: Int? = null
    var category_index: Int? = null
    var photo_url: String? = null
    var poster_name: String? = null
    var poster_reg_date: String? = null
    var poster_start_date: String? = null
    var poster_end_date: String? = null
    var poster_web_site: String? = null
    var is_seek: Int? = null
    var out_line: String? = null
    var target: String? = null
    var period: String? = null
    var benefit: String? = null
    var document_date: String? = null
    var announce_date1: String? = null
    var announce_date2: String? = null
    var finalAnnounce_date: String? = null
    var interview_date: String? = null
    var poster_interest: Int? = null

//    private fun getPosterResponse() {
//        var jsonObject = JSONObject()
//
//        val token = SharedPreferenceController.getAuthorization(this.context!!)
//        val postPosterListResponse: Call<PostPosterListResponse> = networkService.postPosterResponse(token)
//
//        postPosterListResponse.enqueue(object : Callback<PostPosterListResponse> {
//            override fun onFailure(call: Call<PostPosterListResponse>, t: Throwable) {
//                Log.e("user info fail", t.toString())
//            }
//
//
//            override fun onResponse(call: Call<PostPosterListResponse>, response: Response<PostPosterListResponse>) {
//
//                if (response.isSuccessful) {
//
//                    for (i in 0..posterlist!!.size) {
//                        poster_index = posterlist!![i].posters[i].posterIdx
//                        category_index = posterlist!![i].posters[i].categoryIdx
//                        photo_url = posterlist!![i].posters[i].photoUrl
//                        poster_name = posterlist!![i].posters[i].posterName
//                        poster_reg_date = posterlist!![i].posters[i].posterRegDate
//                        poster_start_date = posterlist!![i].posters[i].posterStartDate
//                        poster_end_date = posterlist!![i].posters[i].posterEndDate
//                        poster_web_site = posterlist!![i].posters[i].posterWebSite
//                        is_seek = posterlist!![i].posters[i].isSeek
//                        out_line = posterlist!![i].posters[i].outline
//                        target = posterlist!![i].posters[i].target
//                        period = posterlist!![i].posters[i].period
//                        benefit = posterlist!![i].posters[i].benefit
//                        document_date = posterlist!![i].posters[i].documentDate
//                        announce_date1 = posterlist!![i].posters[i].announceDate1
//                        announce_date2 = posterlist!![i].posters[i].announceDate2
//                        finalAnnounce_date = posterlist!![i].posters[i].finalAnnounceDate
//                        interview_date = posterlist!![i].posters[i].interviewDate
//                        poster_interest = posterlist!![i].posters[i].posterInterest[i]
//                    }
//                }
//            }
//        })
//    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var name: TextView = view.findViewById(R.id.item_name)
        var hash: TextView = view.findViewById(R.id.item_hash)
        var start_date: TextView = view.findViewById(R.id.text_main_detail4_step1_date)
        var end_date: TextView = view.findViewById(R.id.text_main_detail4_step4_date)
        var image: ImageView = view.findViewById(R.id.item_image)

        var progress_bar_left: ImageView = view.findViewById(R.id.iv_progress_left)
        var progress_bar_right: ImageView = view.findViewById(R.id.iv_progress_right)
        var detail_view: LinearLayout = view.findViewById(R.id.ll_detail4_background)

        //은혜 처리
        var title_term_4 : TextView =view.findViewById(R.id.title_main_detail4_term)
        var summary_4 : TextView =view.findViewById(R.id.text_main_detail4_summary)
        var target_4 : TextView =view.findViewById(R.id.text_main_detail4_target)
        var date_4 :TextView =view.findViewById(R.id.text_main_detail4_term)
        var benefit_4 :TextView =view.findViewById(R.id.text_main_detail4_benefit)

//        var title_term_3 : TextView =view.findViewById(R.id.title_main_detail3_term)
//        var summary_3 : TextView =view.findViewById(R.id.text_main_detail3_summary)
//        var target_3 : TextView =view.findViewById(R.id.text_main_detail3_target)
//        var date_3 :TextView =view.findViewById(R.id.text_main_detail3_term)
//        var benefit_3 :TextView =view.findViewById(R.id.text_main_detail3_benefit)


    }

}
