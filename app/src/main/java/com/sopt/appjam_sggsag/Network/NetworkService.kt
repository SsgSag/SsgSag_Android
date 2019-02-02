package com.sopt.appjam_sggsag.Network

import com.google.gson.JsonObject
import com.sopt.appjam_sggsag.Post.*
import retrofit2.Call
import retrofit2.http.*

interface NetworkService {
    //회원가입
    @POST("/users")
    fun postSignUpResponse(
        @Header("Content-Type") content_type: String,
        @Body() body: JsonObject
    ): Call<PostSignUpResponse>

    //로그인
    @POST("/login")
    fun postLoginResponse(
        @Header("Content-Type") content_type: String,
        @Body() body: JsonObject
    ): Call<PostLogInResponse>


    //Poster_승완
    @POST("/posters/show")
    fun posterResponse(
        @Header("Authorization") token: String

    ): Call<PostPosterListResponse>

    //캘린더
    @POST("/todo/show")
    fun postCalendarResponse(
        @Header("Content-Type") content_type: String = "application/json",
        @Header("Authorization") token: String,
        @Body() body: JsonObject
    ): Call<PostCalendarResponse>

    //수동추가
    @POST("/posters/manualAdd")
    fun postCalendaraddResponse(
        @Header("Content-Type") content_type: String,
        @Header("Authorization") token: String,
        @Body() body: JsonObject
    ): Call<PostCalendaraddResponse>

    //수동입력 일정 삭제

    //회원 정보 조회
    @POST("/users/info")
    fun postInfoResponse(
        @Header("Authorization") token: String
    ): Call<PostInfoResponse>

    //포스터 좋아요
    @POST("/posters/like")
    fun postPosterLike(
        @Header("Content-Type") content_type: String,
        @Header("Authorization") token: String,
        @Body() body: JsonObject
    ): Call<PostPosterLike>
}