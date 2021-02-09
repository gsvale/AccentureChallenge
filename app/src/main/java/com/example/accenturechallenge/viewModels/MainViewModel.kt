package com.example.accenturechallenge.viewModels

import android.text.TextUtils
import android.util.Log
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.accenturechallenge.models.Pokemon
import com.example.accenturechallenge.models.PokemonListResult
import com.example.accenturechallenge.models.PokemonResult
import com.example.accenturechallenge.network.NetworkClient
import com.example.accenturechallenge.utils.Constants.POKEMON_LIST_URL
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MainViewModel : ViewModel() {

    private val isInit: MutableLiveData<Boolean> = MutableLiveData(false)

    private val receivedToast: MutableLiveData<String> = MutableLiveData()

    private val listVisibility: MutableLiveData<Int> = MutableLiveData()
    private val messageVisibility: MutableLiveData<Int> = MutableLiveData()
    private val loadingVisibility: MutableLiveData<Int> = MutableLiveData()

    private val messageText: MutableLiveData<String> = MutableLiveData()

    private val pokemonListReceived : MutableLiveData<ArrayList<PokemonResult>> = MutableLiveData()

    private var next : String? = POKEMON_LIST_URL
    private var current : String? = POKEMON_LIST_URL

    init {
        isInit.postValue(true)
        listVisibility.postValue(View.VISIBLE)
        messageVisibility.postValue(View.GONE)
        if(!TextUtils.isEmpty(next)) {
            getPokemonList()
        }
    }

    fun isInit(): MutableLiveData<Boolean>{
        return isInit
    }

    fun getToast(): LiveData<String>{
        return receivedToast
    }

    fun getListVisibility() : LiveData<Int>{
        return listVisibility
    }

    fun getMessageVisibility() : LiveData<Int>{
        return messageVisibility
    }

    fun getLoadingVisibility() : LiveData<Int>{
        return loadingVisibility
    }

    fun getMessageText(): LiveData<String>{
        return messageText
    }

    fun getPokemonListReceived(): LiveData<ArrayList<PokemonResult>>{
        return pokemonListReceived
    }

    fun setCurrent(){
        current = next
    }

    fun shouldGetMorePokemon(): Boolean{
        return current == next
    }

    fun getPokemonList() {

        pokemonListReceived.value = ArrayList()

        loadingVisibility.postValue(View.VISIBLE)

        NetworkClient.create()
            .getPokemonList(next)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                next = it.next
                getPokemonListInfo(it, 0)
            }, {
                messageText.postValue("Error loading pokemon list!")
                messageVisibility.postValue(View.VISIBLE)
                listVisibility.postValue(View.GONE)
                loadingVisibility.postValue(View.GONE)
            })
    }

    private fun getPokemonListInfo(pokemonListResult: PokemonListResult?, index: Int) {

        if (pokemonListResult != null) {

            val pokemon = pokemonListResult.results[index]

            NetworkClient.create()
                .getPokemon(pokemon.url)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    pokemonListReceived.value!!.add(it)
                    if(index < pokemonListResult.results.size - 1){
                        val newIndex = index + 1
                        getPokemonListInfo(pokemonListResult, newIndex)
                    }else{
                        pokemonListReceived.postValue(pokemonListReceived.value)
                        messageVisibility.postValue(View.GONE)
                        listVisibility.postValue(View.VISIBLE)
                        loadingVisibility.postValue(View.GONE)
                    }
                }, {
                    receivedToast.postValue("Error loading pokemon info!")
                })

        }


    }

}