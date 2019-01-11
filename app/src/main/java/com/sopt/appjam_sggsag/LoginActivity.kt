package com.sopt.appjam_sggsag

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import com.sopt.appjam_sggsag.DB.SharedPreferenceController
import com.sopt.appjam_sggsag.Network.NetworkService
import com.sopt.appjam_sggsag.Post.PostLogInResponse
import com.sopt.appjam_sggsag.SignUp.SignUp1
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {

    var auto_click: Int = 0
    val networkService: NetworkService by lazy {
        MyApplication.instance.networkService
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        //자동 로그인
            if (SharedPreferenceController.getAuthorization(this).isNotEmpty()){
                startActivity<MainActivity>()
            }



        window.setFlags(
            WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
            WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)

        setOnBtnClickListener()
    }

    private fun setOnBtnClickListener() {
        btn_auto_login.setOnClickListener {
            auto_click = 1 - auto_click
            if (auto_click == 1){
                btn_auto_login_img.setImageResource(R.drawable.checkbox_round_active)
            } else {btn_auto_login_img.setImageResource(R.drawable.checkbox_round)}
        }

        btn_forgot_pw.setOnClickListener {
            toast("비밀번호 찾기")
        }

        btn_login.setOnClickListener {
            getLoginResponse()
            finish()
        }

        btn_login_signup.setOnClickListener {
            toast("회원가입")
            startActivity<SignUp1>()
            finish()
        }
    }

    private fun getLoginResponse() {
        if (edit_id.text.toString().isNotEmpty() && edit_password.text.toString().isNotEmpty()) {
            val input_email = edit_id.text.toString()
            val input_pw = edit_password.text.toString()
            val jsonObject : JSONObject = JSONObject()
            jsonObject.put("userEmail", input_email)
            jsonObject.put("userPw", input_pw)

            val gsonObject: JsonObject = JsonParser().parse(jsonObject.toString()) as JsonObject

            val postLogInResponse: Call<PostLogInResponse> =
                networkService.postLoginResponse("application/json", gsonObject)
            postLogInResponse.enqueue(object : Callback<PostLogInResponse> {
                override fun onFailure(call: Call<PostLogInResponse>, t: Throwable) {
                    Log.d("Login fail", t.toString())
                }

                override fun onResponse(call: Call<PostLogInResponse>, response: Response<PostLogInResponse>) {
                    if (response.isSuccessful) {
                        Log.d("log값", response.body()?.status.toString())
                        val token = response.body()!!.data.token
                        //저번 시간에 배웠던 SharedPreference에 토큰을 저장!
                        SharedPreferenceController.setAuthorization(this@LoginActivity, token)
                        toast(SharedPreferenceController.getAuthorization(this@LoginActivity))
                        startActivity<MainActivity>()
                        finish()
                    }
                }
            })
        }
    }

}
