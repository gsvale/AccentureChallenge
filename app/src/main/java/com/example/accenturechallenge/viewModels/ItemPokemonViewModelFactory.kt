package com.example.accenturechallenge.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.accenturechallenge.models.PokemonResult

class ItemPokemonViewModelFactory(private val pokemon: PokemonResult) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(PokemonResult::class.java).newInstance(pokemon)
    }

}