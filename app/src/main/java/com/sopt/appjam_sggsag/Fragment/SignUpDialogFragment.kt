package com.sopt.appjam_sggsag.Fragment

import android.app.PendingIntent.getActivity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v7.app.AlertDialog
import com.sopt.appjam_sggsag.R
import kotlinx.android.synthetic.main.dialog_sign_up.*
import android.support.v4.content.ContextCompat.startActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sopt.appjam_sggsag.Career.CareerActivity
import com.sopt.appjam_sggsag.MainActivity
import org.jetbrains.anko.support.v4.startActivity


class SignUpDialogFragment: DialogFragment(){
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.dialog_sign_up, container,false)
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        iv_okay_popup.setOnClickListener {
            startActivity<CareerActivity>()
            dismiss()
        }

        iv_cancel_popup.setOnClickListener {
            dismiss()
        }
    }
}
