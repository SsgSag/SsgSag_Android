package com.sopt.appjam_sggsag.SignUp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.sopt.appjam_sggsag.MainActivity
import com.sopt.appjam_sggsag.MyApplication
import com.sopt.appjam_sggsag.Network.NetworkService
import com.sopt.appjam_sggsag.R
import kotlinx.android.synthetic.main.activity_sign_up4.*
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import org.jetbrains.anko.startActivity
import java.io.File

class SignUp4 : AppCompatActivity() {
    lateinit var interestListServer: Array<Byte>
    //    var interestListServer: ArrayList<Byte> = ArrayList()
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
                    interestListServer= arrayOf(i.toByte())
            }
            Log.d("interest", "진희야 힘내" + interestListServer)
            getSignUpResponseData()
            startActivity<MainActivity>("check_signUp" to 1)
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

//        var userEmail = RequestBody.create(MediaType.parse("text/plain"), SignUp1.getSignUp1.id)
//        var userPw = RequestBody.create(MediaType.parse("text/plain"), SignUp1.getSignUp1.pw)
//        var userName = RequestBody.create(MediaType.parse("text/plain"), SignUp2.getSignUp2.name)
//        var userUniv = RequestBody.create(MediaType.parse("text/plain"), SignUp3.getSignUp3.school)
//        var userMajor = RequestBody.create(MediaType.parse("text/plain"), SignUp3.getSignUp3.major)
//        var userStudentNum = RequestBody.create(MediaType.parse("text/plain"), SignUp3.getSignUp3.sid)
//        var userBirth = RequestBody.create(MediaType.parse("text/plain"), SignUp2.getSignUp2.birth)
//        var userPushAllow = RequestBody.create(MediaType.parse("text/plain"), 1)
//        var userInfoAllow = RequestBody.create(MediaType.parse("text/plain"), 1)
//        var userInterest = RequestBody.create(MediaType.parse("text/plain"), interestListServer)
//        var profile = RequestBody.create(MediaType.parse("text/plain"), )

//        if(SignUp2.getSignUp2.btn==1)
//            gender="female"
//        else if(SignUp2.getSignUp2.btn==2)
//            gender="male"
//
//        var userGender = RequestBody.create(MediaType.parse("text/plain"), gender)
//
//        val file : File = File(SignUp2.getSignUp2.imageURI)
//        val requestfile : RequestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file)
//        val data : MultipartBody.Part = MultipartBody.Part.createFormData("photo", file.name, requestfile)

//
//        //Gson 라이브러리의 Json Parser을 통해 객체를 Json으로!
//        val gsonObject = JsonParser().parse(jsonObject.toString()) as JsonObject
//        val postSignUpResponse: Call<PostSignUpResponse> =
//            networkService.postSignUpResponse(gsonObject)
//        postSignUpResponse.enqueue(object : Callback<PostSignUpResponse> {
//            override fun onFailure(call: Call<PostSignUpResponse>, t: Throwable) {
//                Log.e("sign up fail", t.toString())
//            }
//
//            //통신 성공 시 수행되는 메소드
//            override fun onResponse(call: Call<PostSignUpResponse>, response: Response<PostSignUpResponse>) {
//                if (response.isSuccessful) {
//                    toast(response.body()!!.message)
//                    finish()
//                }
//            }
//        })
    }
}
