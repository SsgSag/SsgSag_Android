package com.sopt.appjam_sggsag.Post

data class PostInfoResponse(
    val status : String,
    val message : String,
    val data : InfoData
)

data class InfoData(
    val userIdx : Int,
    val userEmail : String,
    val userPw : String,
    val userId : String,
    val userName : String,
    val userUniv : String,
    val userMajor : String,
    val userStudentNum : String,
    val userGender : String,
    val userBirth : String,
    val userSignOutDate : String,
    val userSignInDate : String,
    val userPushAllow : Int,
    val userIsSeeker : Int,
    val userCnt : Int,
    val userInfoAllow : Int,
    val userProfileUrl : String,
    val userAlreadyOut : Int,
    val userGrade : Int
)