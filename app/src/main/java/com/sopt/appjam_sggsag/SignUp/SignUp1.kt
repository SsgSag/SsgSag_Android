package com.sopt.appjam_sggsag.SignUp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import com.sopt.appjam_sggsag.LoginActivity
import com.sopt.appjam_sggsag.R
import kotlinx.android.synthetic.main.activity_sign_up1.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

class SignUp1 : AppCompatActivity() {

    object getSignUp1 {
        lateinit var id :String
        lateinit var pw :String
    }

    lateinit var pw_check: String

    override fun onBackPressed() {
        startActivity<LoginActivity>()
        finish()
        super.onBackPressed()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up1)

//        this.window.decorView
//            .systemUiVisibility=(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR)

//        this.window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN)

        iv_back_1.setOnClickListener {
            startActivity<LoginActivity>()
            finish()
        }

        et_id.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                changeInput()
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }
        })

        et_pw.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                changeInput()
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }
        })

        et_pw_check.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                changeInput()

            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }
        })
    }

    private fun changeInput() {
        getEditText()
        if (getSignUp1.id.isEmpty() || getSignUp1.pw.isEmpty() || pw_check.isEmpty())
            iv_next_1.setImageResource(R.drawable.bt_next_unactive)
        else
            goToNext()
    }


    private fun getEditText() {
        getSignUp1.id = et_id.text.toString()
        getSignUp1.pw = et_pw.text.toString()
        pw_check = et_pw_check.text.toString()
    }

    private fun goToNext() {
        getEditText()
        if (getSignUp1.id.isNotEmpty() && getSignUp1.pw.isNotEmpty() && pw_check.isNotEmpty()) {
            if(getSignUp1.pw==pw_check){
                Log.d("show input","aaaaaaaaa"+getSignUp1.id+"   "+getSignUp1.pw)
                iv_next_1.setImageResource(R.drawable.bt_next_active)
                iv_next_1.setOnClickListener {
                    startActivity<SignUp2>()
                    finish()
                }
            }else
                toast("비밀번호를 확인해 주세요")

        }
    }
}
