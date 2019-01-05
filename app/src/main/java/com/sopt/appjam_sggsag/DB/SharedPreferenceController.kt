package com.sopt.appjam_sggsag.DB

import android.content.Context
import android.content.SharedPreferences

object SharedPreferenceController {
    private val IS_FIRST: String = "is_first"
    private val USER_NAME = "MYKEY"
    private val myAuth = "myAuth"

    fun setNotFirst(ctx: Context){
        val preference : SharedPreferences = ctx.getSharedPreferences(IS_FIRST, Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = preference.edit()
        editor.putBoolean(IS_FIRST, false)
        editor.commit()
    }

    fun checkFirst(ctx: Context): Boolean {
        val preference : SharedPreferences = ctx.getSharedPreferences(IS_FIRST, Context.MODE_PRIVATE)
        return preference.getBoolean(IS_FIRST, true)
    }

    fun setAuthorization(context: Context, authorization : String){
        val pref = context.getSharedPreferences(USER_NAME, Context.MODE_PRIVATE) //현재 내 기기에서만 볼수 있는 데이터
        val editor = pref.edit()
        editor.putString(myAuth, authorization)
        editor.commit()
    }

    fun getAuthorization(context: Context) : String {
        val pref = context.getSharedPreferences(USER_NAME, Context.MODE_PRIVATE) //현재 내 기기에서만 볼수 있는 데이터
        return pref.getString(myAuth, "")
    }

    fun clearSPC(context: Context){
        val pref = context.getSharedPreferences(USER_NAME, Context.MODE_PRIVATE) //현재 내 기기에서만 볼수 있는 데이터
        val editor = pref.edit()
        editor.clear()
        editor.commit()
    }
}