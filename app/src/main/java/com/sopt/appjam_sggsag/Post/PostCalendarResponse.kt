package com.sopt.appjam_sggsag.Post


data class PostCalendarResponse(
    val status : Int,
    val message : String,
    val data : ArrayList<CalendarData>
)
data class CalendarData(
    val posterIdx : Int,
    val categoryIdx : Int,
    val isPoster : Int,
    val isComplete : Int,
    val isEnded : Int,
    val posterName : String,
    val outline : String,
    val posterStartDate : String,
    val posterEndDate : String,
    val dday : Int,
    val length : Int
)
