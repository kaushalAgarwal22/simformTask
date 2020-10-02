package com.example.simformtask.model

import com.google.gson.annotations.SerializedName

data class UserData(
    @SerializedName("results")
    val results: List<Result>
)