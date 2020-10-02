package com.example.simformtask.network

import com.example.simformtask.model.UserData
import retrofit2.Call
import retrofit2.http.GET

interface UserService {

    @GET("?results=100")
    fun getUsers(): Call<UserData>

}