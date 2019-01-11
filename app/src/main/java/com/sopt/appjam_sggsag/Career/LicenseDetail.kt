package com.sopt.appjam_sggsag.Career

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.sopt.appjam_sggsag.Fragment.Career.Career3Fragment
import com.sopt.appjam_sggsag.R
import kotlinx.android.synthetic.main.activity_award_detail.*
import kotlinx.android.synthetic.main.activity_license_detail.*

class LicenseDetail : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_license_detail)

        sendData()

        setBtnOnClickListener()
    }

    private fun setBtnOnClickListener(){
        btn_certificate_close.setOnClickListener {
            finish()
        }
    }

    private fun sendData(){
        btn_license_save.setOnClickListener {
            var title : String = et_license_title.text.toString()
            var note: String = et_license_note.text.toString()
            var year : String = et_license_year.text.toString()
            var month : String = et_license_month.text.toString()

            var date : String = year + "." + month

            if(title.isNotEmpty() && note.isNotEmpty() && year.isNotEmpty() && month.isNotEmpty()){
                val intent = Intent(this@LicenseDetail, Career3Fragment::class.java)
                intent.putExtra("title", title)
                intent.putExtra("note", note)
                intent.putExtra("date", date)

                setResult(Activity.RESULT_OK, intent)
            }
            finish()
        }
    }
}
