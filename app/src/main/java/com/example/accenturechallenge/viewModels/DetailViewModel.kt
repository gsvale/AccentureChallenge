package com.example.accenturechallenge.viewModels

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.accenturechallenge.models.PokemonResult
import com.squareup.picasso.Picasso
import java.lang.StringBuilder

class DetailViewModel(pokemon: PokemonResult) : ViewModel() {


    private val frontDefault: MutableLiveData<String> = MutableLiveData()
    private val backDefault: MutableLiveData<String> = MutableLiveData()
    private val frontShiny: MutableLiveData<String> = MutableLiveData()
    private val backShiny: MutableLiveData<String> = MutableLiveData()

    private val id: MutableLiveData<String> = MutableLiveData()

    private val baseExperience: MutableLiveData<String> = MutableLiveData()
    private val height: MutableLiveData<String> = MutableLiveData()
    private val weight: MutableLiveData<String> = MutableLiveData()

    private val abilities: MutableLiveData<String> = MutableLiveData()
    private val types: MutableLiveData<String> = MutableLiveData()

    init{
        frontDefault.postValue(pokemon.sprites.frontDefault)
        backDefault.postValue(pokemon.sprites.backDefault)
        frontShiny.postValue(pokemon.sprites.frontShiny)
        backShiny.postValue(pokemon.sprites.backShiny)

        id.postValue(pokemon.id.toString())
        baseExperience.postValue(pokemon.baseExperience)
        height.postValue(pokemon.height)
        weight.postValue(pokemon.weight)

        val abilitiesStringBuilder = StringBuilder()

        for(i in 0 until pokemon.abilities.size){
            abilitiesStringBuilder.append(pokemon.abilities[i].ability.name)
            if(i < pokemon.abilities.size - 1){
                abilitiesStringBuilder.append("\n")
            }
        }

        abilities.postValue(abilitiesStringBuilder.toString())

        val typesStringBuilder = StringBuilder()

        for(i in 0 until pokemon.types.size){
            typesStringBuilder.append(pokemon.types[i].type.name)
            if(i < pokemon.types.size - 1){
                typesStringBuilder.append("\n")
            }
        }

        types.postValue(typesStringBuilder.toString())


    }

    fun getFrontDefault() : LiveData<String> {
        return frontDefault
    }

    fun getBackDefault() : LiveData<String> {
        return backDefault
    }

    fun getFrontShiny() : LiveData<String> {
        return frontShiny
    }

    fun getBackShiny() : LiveData<String> {
        return backShiny
    }

    fun getId() : LiveData<String> {
        return id
    }

    fun getBaseExperience() : LiveData<String> {
        return baseExperience
    }

    fun getHeight() : LiveData<String> {
        return height
    }

    fun getWeight() : LiveData<String> {
        return weight
    }

    fun getAbilities() : LiveData<String> {
        return abilities
    }

    fun getTypes() : LiveData<String> {
        return types
    }

    object DataBindingAdapter {

        @BindingAdapter("app:cardImage")
        @JvmStatic
        fun loadImage(view: ImageView, cardImage: String?) {
            // Use Picasso to load avatar from url
            Picasso.get()
                .load(cardImage)
                .into(view)
        }

    }


}