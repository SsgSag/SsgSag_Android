package com.sopt.appjam_sggsag.Adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import com.sopt.appjam_sggsag.Data.TodoListData
import com.sopt.appjam_sggsag.MainActivity
import com.sopt.appjam_sggsag.R
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

class TodoListRecyclerViewAdapter(val ctx: Context, val dataList: ArrayList<TodoListData>) :
    RecyclerView.Adapter<TodoListRecyclerViewAdapter.Holder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view: View = LayoutInflater.from(ctx).inflate(R.layout.rv_item_todo_list, parent, false)
        return Holder(view)
    }

    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.title.text = dataList[position].title
        holder.dday.text = dataList[position].date
        holder.item_btn.setOnClickListener {
            ctx.toast("메인엑티비티로")
            ctx.startActivity<MainActivity>()
        }
    }

    inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.tv_rv_todo_list) as TextView
        val dday: TextView = itemView.findViewById(R.id.tv_rv_d_day) as TextView
        val item_btn : RelativeLayout = itemView.findViewById(R.id.btn_rv_item_todo_list) as RelativeLayout
    }

}