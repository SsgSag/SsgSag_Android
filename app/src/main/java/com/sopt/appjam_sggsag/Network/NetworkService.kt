package com.sopt.appjam_sggsag.Network

import com.google.gson.JsonObject
import com.sopt.appjam_sggsag.Post.PostLogInResponse
import com.sopt.appjam_sggsag.Post.PostPosterListResponse
import com.sopt.appjam_sggsag.Post.PostSignUpResponse
import okhttp3.RequestBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.http.*

interface NetworkService{
    //회원가입
    @Multipart
    @POST("/users")
    fun postSignUpResponse(
        @Part("userEmail") userEmail : RequestBody,
        @Part("userPw") userPw : RequestBody,
        @Part("userName") userName : RequestBody,
        @Part("userUniv") userUniv : RequestBody,
        @Part("userMajor") userMajor : RequestBody
    ) : Call<PostSignUpResponse> //return type

    //로그인
    @POST("/login")
    fun postLoginResponse(
        @Header("Content-Type") content_type : String,
        @Body() body : JsonObject
    ) : Call<PostLogInResponse>
    //Poster_승완
    @POST("posters/show")
    fun postPosterResponse(
        @Header("Authorization") token : String//,
//        @Header("Content-Type") content_type : JSONObject
//        @Body() body : JsonObject
    ): Call<PostPosterListResponse>
}