package com.example.accenturechallenge.network

import com.example.accenturechallenge.models.PokemonListResult
import com.example.accenturechallenge.models.PokemonResult
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Url

interface NetworkInterface {

    @GET
    fun getPokemonList(@Url url: String?): Observable<PokemonListResult>

    @GET
    fun getPokemon(@Url url: String?): Observable<PokemonResult>

}