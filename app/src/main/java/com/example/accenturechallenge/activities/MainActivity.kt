package com.example.accenturechallenge.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.accenturechallenge.R
import com.example.accenturechallenge.adapters.PokemonListAdapter
import com.example.accenturechallenge.databinding.ActivityMainBinding
import com.example.accenturechallenge.models.PokemonResult
import com.example.accenturechallenge.viewModels.MainViewModel
import java.util.ArrayList

class MainActivity : AppCompatActivity() {

    /*

    This project/application contains in the main screen
    a RecyclerView with pokemon items, starting with 20, then by scrolling down
    another 20 are fetched and received and the lis is updated.
    In each item we can see each pokemon name and front default image and by clicking
    in a item, we go to the detail activity where we see more images related to the pokemon
    and other information regarding.

     */

    private lateinit var mBinding: ActivityMainBinding
    private lateinit var mViewModel: MainViewModel

    private var isLoadingData = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Set DataBinding instance
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        // Set ViewModel
        mViewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        // Observers
        mViewModel.isInit().observe(this, Observer { initViews() })
        mViewModel.getToast().observe(this, Observer { handleToast(it) })
        mViewModel.getPokemonListReceived().observe(this, Observer { handleList(it) })

        // Binding ViewModel
        mBinding.viewModel = mViewModel
        mBinding.lifecycleOwner = this

    }

    private fun initViews() {

        // Init RecyclerView and its adapter
        val adapter = PokemonListAdapter(this, ArrayList())
        mBinding.mainRv.adapter = adapter
        val linearLayoutManager = LinearLayoutManager(applicationContext)
        mBinding.mainRv.layoutManager = linearLayoutManager

        // Add OnScrollListener to update contents of RecyclerView, getting items for next pages
        mBinding.mainRv.addOnScrollListener(object : RecyclerView.OnScrollListener() {

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val visibleItemCount = linearLayoutManager.childCount
                val totalItemCount = linearLayoutManager.itemCount
                val firstVisibleItemPosition = linearLayoutManager.findFirstVisibleItemPosition()

                if (visibleItemCount + firstVisibleItemPosition >= totalItemCount
                    && firstVisibleItemPosition >= 0
                    && !mViewModel.shouldGetMorePokemon()
                    && !isLoadingData
                ) {
                    isLoadingData = true
                    mViewModel.setCurrent()
                    mViewModel.getPokemonList()
                }

            }
        })

    }


    private fun handleToast(error: String) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
    }

    private fun handleList(pokemonList: ArrayList<PokemonResult>) {
        (mBinding.mainRv.adapter as PokemonListAdapter).addItems(pokemonList)
        isLoadingData = false
    }

}