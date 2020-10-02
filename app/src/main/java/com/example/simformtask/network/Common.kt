package com.example.simformtask.network

import android.content.Context
import android.net.ConnectivityManager
import android.util.Log

object Common {

    val BASE_URL = "https://randomuser.me/api/"

    val userService: UserService
        get() = RetrofitClient.getClient(BASE_URL).create(UserService::class.java)

    public fun hasNetworkAvailable(context: Context): Boolean {
        val service = Context.CONNECTIVITY_SERVICE
        val manager = context.getSystemService(service) as ConnectivityManager?
        val network = manager?.activeNetworkInfo
        Log.d("Debug", "hasNetworkAvailable: ${(network != null)}")
        return (network != null)
    }
}