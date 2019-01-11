package com.sopt.appjam_sggsag.Network

import com.google.gson.JsonObject
import com.sopt.appjam_sggsag.Post.*
import okhttp3.MultipartBody
import okhttp3.RequestBody
import org.json.JSONObject
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
    @POST("posters/show")
    fun postPosterResponse(
        @Header("Authorization") token: String//,
//        @Header("Content-Type") content_type : JSONObject
//        @Body() body : JsonObject
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
}