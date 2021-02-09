package com.example.accenturechallenge.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class PokemonResult(@SerializedName("name") val name: String,
                         @SerializedName("id") val id: Int,
                         @SerializedName("sprites") val sprites: Sprites,
                         @SerializedName("abilities") val abilities: ArrayList<Abilities>,
                         @SerializedName("types") val types: ArrayList<Types>,
                         @SerializedName("base_experience") val baseExperience: String,
                         @SerializedName("height") val height: String,
                         @SerializedName("weight") val weight: String
                         ) : Serializable {


}