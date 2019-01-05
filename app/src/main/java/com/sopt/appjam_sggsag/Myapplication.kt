package com.sopt.appjam_sggsag

import android.app.Application
import com.sopt.appjam_sggsag.Network.NetworkService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MyApplication : Application() {

    private val baseURL = "http://54.180.79.158:8080/"
    lateinit var networkService: NetworkService
    companion object {
        lateinit var instance: MyApplication
    }
    override fun onCreate() {
        super.onCreate()
        instance = this
        buildNetWork()
    }
    fun buildNetWork() {
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(baseURL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        networkService = retrofit.create(NetworkService::class.java)
    }

}
