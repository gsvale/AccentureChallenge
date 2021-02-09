package com.example.accenturechallenge.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Type(@SerializedName("name") val name: String,
                @SerializedName("url") val url: String) : Serializable {

}