package com.sopt.appjam_sggsag.Adapter.Career

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.sopt.appjam_sggsag.Data.Career.CertificateListData
import com.sopt.appjam_sggsag.R
import org.jetbrains.anko.find

class CertificateRecyclerViewAdapter(val ctx : Context, val dataList: ArrayList<CertificateListData>) : RecyclerView.Adapter<CertificateRecyclerViewAdapter.Holder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view : View = LayoutInflater.from(ctx).inflate(R.layout.rv_item_award_list, parent, false)
        return Holder(view)
    }

    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.title.text = dataList[position].title
        holder.content.text = dataList[position].content
        holder.time.text = dataList[position].time
    }

    inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val title : TextView = itemView.find(R.id.career_card_title) as TextView
        val content : TextView = itemView.find(R.id.career_card_content) as TextView
        val time : TextView = itemView.find(R.id.career_card_time) as TextView
    }
}