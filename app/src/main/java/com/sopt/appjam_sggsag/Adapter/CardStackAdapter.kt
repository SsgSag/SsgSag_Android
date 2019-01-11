package com.sopt.appjam_sggsag.Adapter


import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.sopt.appjam_sggsag.R

class CardStackAdapter(
    private var spots: List<Spot> = emptyList()
) : RecyclerView.Adapter<CardStackAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(inflater.inflate(R.layout.item_spot, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val spot = spots[position]
        holder.name.text = "${spot.id}. ${spot.name}"
        holder.city.text = spot.city
        Glide.with(holder.image)
            .load(spot.url)
            .into(holder.image)

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
                        Log.e("왼쪽", "클릭")

                    } else {//card의 오른쪽 클릭
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

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name: TextView = view.findViewById(R.id.item_name)
        var city: TextView = view.findViewById(R.id.item_city)
        var image: ImageView = view.findViewById(R.id.item_image)
    }

}
