package com.example.accenturechallenge.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Abilities(@SerializedName("ability") val ability: Ability) : Serializable {


}