package com.example.accenturechallenge.adapters

import android.content.Context
import android.content.Intent
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.accenturechallenge.R
import com.example.accenturechallenge.activities.DetailActivity
import com.example.accenturechallenge.databinding.AdapterItemPokemonBinding
import com.example.accenturechallenge.models.PokemonResult
import com.example.accenturechallenge.viewModels.ItemPokemonViewModel
import com.example.accenturechallenge.viewModels.ItemPokemonViewModelFactory
import com.example.accenturechallenge.viewModels.MainViewModel
import java.util.ArrayList

class PokemonListAdapter(private val context: Context, private val pokemonList: ArrayList<PokemonResult>)
    : RecyclerView.Adapter<PokemonListAdapter.PokemonAdapterViewHolder>() {

    private var layoutInflater: LayoutInflater? = null

    // Create View Holders
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonAdapterViewHolder {

        if (layoutInflater == null) {
            layoutInflater = LayoutInflater.from(parent.context)
        }

        // Set DataBinding instance
        val binding = DataBindingUtil.inflate<AdapterItemPokemonBinding>(
            layoutInflater!!,
            R.layout.adapter_item_pokemon,
            parent,
            false
        )

        return PokemonAdapterViewHolder(context,binding)
    }

    // OnBind method
    override fun onBindViewHolder(holder: PokemonAdapterViewHolder, position: Int) {
        holder.bindPokemon(pokemonList[position])
    }

    // Get number/count of items of adapter
    override fun getItemCount(): Int {
        return pokemonList.size
    }

    // Method to add items
    fun addItems(list: List<PokemonResult>?) {
        if (list != null) {
            pokemonList.addAll(list)
        }
        notifyDataSetChanged()
    }

    class PokemonAdapterViewHolder(private val context: Context , private val binding: AdapterItemPokemonBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindPokemon(pokemon: PokemonResult) {

            // Set and Binding ViewModel
            val viewModel = ItemPokemonViewModel(pokemon)

            // Observer
            viewModel.getGoToDetails().observe(context as AppCompatActivity, Observer {
                if(it){
                    val intent = Intent(context, DetailActivity::class.java)
                    intent.putExtra(PokemonResult::class.java.toString(), pokemon)
                    context.startActivity(intent)
                }
            })

            binding.viewModel = viewModel
            binding.lifecycleOwner = context
        }


    }


}