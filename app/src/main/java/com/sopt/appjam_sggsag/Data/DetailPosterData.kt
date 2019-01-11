package com.sopt.appjam_sggsag.Data



data class DetailPosterData(
    val posterIdx : Int,
    val categoryIdx : Int,
    val photoUrl: String,
    val posterName : String,
    val posterRegDate : String?,
    val posterStartDate: String?,
    val posterEndDate: String?,
    val posterWebSite: String?,
    val isSeek : Int,
    val outline : String?,
    val target : String?,
    val period : String?,
    val benefit : String?,
    val documentDate : String?,
    val announceDate1 : String?,
    val announceDate2 : String?,
    val finalAnnounceDate : String?,
    val interviewDate : String?
)