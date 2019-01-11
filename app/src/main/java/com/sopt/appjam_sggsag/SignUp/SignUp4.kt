package com.sopt.appjam_sggsag.SignUp


import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import com.sopt.appjam_sggsag.MainActivity
import com.sopt.appjam_sggsag.MyApplication
import com.sopt.appjam_sggsag.Network.NetworkService
import com.sopt.appjam_sggsag.Post.PostSignUpResponse
import com.sopt.appjam_sggsag.R
import kotlinx.android.synthetic.main.activity_sign_up4.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast
import org.json.JSONArray
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignUp4 : AppCompatActivity() {

    val interestListServer = JSONArray()
    var interestList: ArrayList<Int> = arrayListOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)
    var check = 0
    lateinit var gender: String

    val networkService: NetworkService by lazy {
        MyApplication.instance.networkService
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up4)
        setOnBtnClickListener()
    }

    override fun onBackPressed() {
        startActivity<SignUp3>()
        finish()
        super.onBackPressed()
    }

    private fun setOnBtnClickListener() {

        iv_back_4.setOnClickListener {
            startActivity<SignUp3>()
            finish()
        }
        // 기획/아이디어
        btn_idea.setOnClickListener {
            if (interestList[0] == 0) {
                interestList[0] = 1
                btn_idea.setImageResource(R.drawable.bt_preference_idea_active)
            } else {
                interestList[0] = 0
                btn_idea.setImageResource(R.drawable.bt_preference_idea_unactive)
            }
            changInput()
        }

        btn_camera.setOnClickListener {
            if (interestList[7] == 0) {
                interestList[7] = 1
                btn_camera.setImageResource(R.drawable.bt_preference_camera_active)
            } else {
                interestList[7] = 0
                btn_camera.setImageResource(R.drawable.bt_preference_camera_unactive)
            }
            changInput()
        }

        btn_design.setOnClickListener {
            if (interestList[2] == 0) {
                interestList[2] = 1
                btn_design.setImageResource(R.drawable.bt_preference_design_active)
            } else {
                interestList[2] = 0
                btn_design.setImageResource(R.drawable.bt_preference_design_unactive)
            }
            changInput()
        }

        btn_marketing.setOnClickListener {
            if (interestList[5] == 0) {
                interestList[5] = 1
                btn_marketing.setImageResource(R.drawable.bt_preference_marketing_active)
            } else {
                interestList[5] = 0
                btn_marketing.setImageResource(R.drawable.bt_preference_marketing_unactive)
            }
            changInput()
        }
        btn_tech.setOnClickListener {
            if (interestList[11] == 0) {
                interestList[11] = 1
                btn_tech.setImageResource(R.drawable.bt_preference_tech_active)
            } else {
                interestList[11] = 0
                btn_tech.setImageResource(R.drawable.bt_preference_tech_unactive)
            }
            changInput()
        }
        btn_literature.setOnClickListener {
            if (interestList[3] == 0) {
                interestList[3] = 1
                btn_literature.setImageResource(R.drawable.bt_preference_literature_active)
            } else {
                interestList[3] = 0
                btn_literature.setImageResource(R.drawable.bt_preference_literature_unactive)
            }
            changInput()
        }
        btn_scholarship.setOnClickListener {
            if (interestList[10] == 0) {
                interestList[10] = 1
                btn_scholarship.setImageResource(R.drawable.bt_preference_sholarship_active)
            } else {
                interestList[10] = 0
                btn_scholarship.setImageResource(R.drawable.bt_preference_sholarship_unactive)
            }
            changInput()
        }
        btn_health.setOnClickListener {
            if (interestList[9] == 0) {
                interestList[9] = 1
                btn_health.setImageResource(R.drawable.bt_preference_health_active)
            } else {
                interestList[9] = 0
                btn_health.setImageResource(R.drawable.bt_preference_health_unactive)
            }
            changInput()
        }
        btn_startup.setOnClickListener {
            if (interestList[8] == 0) {
                interestList[8] = 1
                btn_startup.setImageResource(R.drawable.bt_preference_startup_active)
            } else {
                interestList[8] = 0
                btn_startup.setImageResource(R.drawable.bt_preference_startup_unactive)
            }
            changInput()
        }
        btn_art.setOnClickListener {
            if (interestList[4] == 0) {
                interestList[4] = 1
                btn_art.setImageResource(R.drawable.bt_preference_art_active)
            } else {
                interestList[4] = 0
                btn_art.setImageResource(R.drawable.bt_preference_art_unactive)
            }
            changInput()
        }
        btn_economy.setOnClickListener {
            if (interestList[1] == 0) {
                interestList[1] = 1
                btn_economy.setImageResource(R.drawable.bt_preference_economy_active)
            } else {
                interestList[1] = 0
                btn_economy.setImageResource(R.drawable.bt_preference_economy_unactive)
            }
            changInput()
        }
        btn_society.setOnClickListener {
            if (interestList[6] == 0) {
                interestList[6] = 1
                btn_society.setImageResource(R.drawable.bt_preference_society_active)
            } else {
                interestList[6] = 0
                btn_society.setImageResource(R.drawable.bt_preference_society_unactive)
            }
            changInput()
        }
    }

    private fun goToNext() {
        btn_start.setImageResource(R.drawable.bt_start)
        btn_start.setOnClickListener {
            for (i in 0..11) {
                if (interestList[i] == 1)
                    interestListServer.put(i)
                //interestListServer = arrayOf(i.toByte())
            }
            getSignUpResponseData()
            startActivity<MainActivity>()
            finish()
        }
    }

    private fun changInput() {
        for (i in 0..11)
            if (interestList[i] == 1)
                check++

        if (check > 0) {
            goToNext()
            check = 0
        } else
            btn_start.setImageResource(R.drawable.bt_start_unactive)
    }

    private fun getSignUpResponseData() {

        var userEmail = SignUp1.getSignUp1.id
        var userPw =  SignUp1.getSignUp1.pw
        var userName = SignUp2.getSignUp2.name
        var userUniv = SignUp3.getSignUp3.school
        var userMajor = SignUp3.getSignUp3.major
        var userStudentNum = SignUp3.getSignUp3.sid
        var userBirth = SignUp2.getSignUp2.birth
        var userId = SignUp2.getSignUp2.email

        if(SignUp2.getSignUp2.btn==1)
            gender="female"
        else if(SignUp2.getSignUp2.btn==2)
            gender="male"

        var userGender = gender

        val jsonObject : JSONObject = JSONObject()
        jsonObject.put("userEmail", userEmail)
        jsonObject.put("userPw", userPw)
        jsonObject.put("userId", userId)
        jsonObject.put("userName", userName)
        jsonObject.put("userUniv", userUniv)
        jsonObject.put("userMajor", userMajor)
        jsonObject.put("userStudentNum", userStudentNum)
        jsonObject.put("userBirth", userBirth)
        jsonObject.put("userGender", gender)
        jsonObject.put("userInterest", interestListServer)

        val gsonObject: JsonObject = JsonParser().parse(jsonObject.toString()) as JsonObject

        val postSignUpResponse: Call<PostSignUpResponse> =
            networkService.postSignUpResponse("application/json", gsonObject)
        postSignUpResponse.enqueue(object : Callback<PostSignUpResponse> {
            override fun onFailure(call: Call<PostSignUpResponse>, t: Throwable) {
                Log.d("signup fail", t.toString())
            }

            override fun onResponse(call: Call<PostSignUpResponse>, response: Response<PostSignUpResponse>) {
                if (response.isSuccessful) {
                    Log.d("log값", response.body()?.status.toString()) //status가 201이면 성공적

                    if(response.body()?.status == "201"){
                        toast("status : " + response.body()?.status)
                        startActivity<MainActivity>()
                        finish()
                    }
                    else
                    {
                        Log.d("status값", response.body()?.status.toString())
                        toast("status : " + response.body()?.status)
                    }
                }
            }
        })


    }
}