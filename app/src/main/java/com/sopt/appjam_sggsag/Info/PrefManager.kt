package com.sopt.appjam_sggsag.Info

import android.content.Context
import android.content.SharedPreferences

class PrefManager(internal var _context: Context) {
    lateinit var pref: SharedPreferences
    lateinit var editor: SharedPreferences.Editor
    // shared pref mode
    internal var PRIVATE_MODE = 0

    fun PrefManager(context: Context) {
        this._context = context
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE)
        editor = pref.edit()
    }

    fun setFirstTimeLaunch(isFirstTime: Boolean) {
        editor.putBoolean(IS_FIRST_TIME_LAUNCH, isFirstTime)
        editor.commit()
    }

    fun isFirstTimeLaunch(): Boolean {
        return pref.getBoolean(IS_FIRST_TIME_LAUNCH, true)
    }

    companion object {

        // Shared preferences file name
        private val PREF_NAME = "welcome"
        private val IS_FIRST_TIME_LAUNCH = "IsFirstTimeLaunch"
    }
}