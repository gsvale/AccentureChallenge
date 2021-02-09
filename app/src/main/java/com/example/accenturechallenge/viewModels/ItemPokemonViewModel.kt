package com.example.accenturechallenge.viewModels

import android.content.Intent
import android.os.Parcelable
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.accenturechallenge.R
import com.example.accenturechallenge.activities.DetailActivity
import com.example.accenturechallenge.models.PokemonResult
import com.squareup.picasso.Picasso

class ItemPokemonViewModel(pokemon: PokemonResult) : ViewModel() {

    private val goToDetails: MutableLiveData<Boolean> = MutableLiveData(false)

    private val name: MutableLiveData<String> = MutableLiveData()
    private val image: MutableLiveData<String> = MutableLiveData()

    init{
        name.postValue(pokemon.name)
        image.postValue(pokemon.sprites.frontDefault)
    }

    fun getName(): LiveData<String> {
        return name
    }

    fun getImage(): LiveData<String> {
        return image
    }

    fun getGoToDetails(): LiveData<Boolean>{
        return goToDetails
    }

    fun onItemClick(view: View) {
        goToDetails.postValue(true)
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