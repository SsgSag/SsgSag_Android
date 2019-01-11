package com.sopt.appjam_sggsag.Data

data class EventList(
    var startDay: String?,
    var endDay: String?,
    var year: Int,
    var month: Int,
    var day: Int,
    var eventName: String,
    var category: Int,
    var dday: Int
)