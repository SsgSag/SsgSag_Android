package com.sopt.appjam_sggsag.SignUp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.WindowManager
import com.sopt.appjam_sggsag.R
import kotlinx.android.synthetic.main.activity_sign_up3.*
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.jetbrains.anko.startActivity

class SignUp3 : AppCompatActivity() {

    object getSignUp3 {
        lateinit var school: String
        lateinit var major: String
        lateinit var grade: String
        lateinit var sid: String
    }

    override fun onBackPressed() {
        startActivity<SignUp2>()
        finish()
        super.onBackPressed()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up3)
        this.window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN)

        iv_back_3.setOnClickListener {
            startActivity<SignUp2>()
//            overridePendingTransition(R.anim.anim_slide_in_right, R.anim.exit_out_left)
            finish()
        }

        et_school.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                changeInput()
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
        })

        et_major.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                changeInput()
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
        })

        et_grade.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                changeInput()
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
        })

        et_sid.addTextChangedListener(object : TextWatcher {
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
        if (getSignUp3.school.isEmpty() || getSignUp3.major.isEmpty() || getSignUp3.grade.isEmpty() || getSignUp3.sid.isEmpty())
            iv_next_3.setImageResource(R.drawable.bt_next_unactive)
        else
            goToNext()
    }

    private fun getEditText() {
        getSignUp3.school = et_school.text.toString()
        getSignUp3.major = et_major.text.toString()
        getSignUp3.grade = et_grade.text.toString()
        getSignUp3.sid = et_sid.text.toString()
    }

    private fun goToNext() {
        getEditText()
        if (getSignUp3.school.isNotEmpty() && getSignUp3.major.isNotEmpty() && getSignUp3.grade.isNotEmpty() && getSignUp3.sid.isNotEmpty()) {
            iv_next_3.setImageResource(R.drawable.bt_next_active)
            iv_next_3.onClick {
                startActivity<SignUp4>()
                finish()
            }
        }
    }
}
