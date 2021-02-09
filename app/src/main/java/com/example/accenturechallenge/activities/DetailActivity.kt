package com.example.accenturechallenge.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.accenturechallenge.R
import com.example.accenturechallenge.databinding.ActivityDetailBinding
import com.example.accenturechallenge.databinding.ActivityMainBinding
import com.example.accenturechallenge.models.PokemonResult
import com.example.accenturechallenge.viewModels.DetailViewModel
import com.example.accenturechallenge.viewModels.DetailViewModelFactory
import com.example.accenturechallenge.viewModels.MainViewModel

class DetailActivity : AppCompatActivity() {


    private lateinit var mBinding: ActivityDetailBinding
    private lateinit var mViewModel: DetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Set DataBinding instance
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_detail)

        // Get Intent Bundle
        val bundle = intent.extras
        var pokemon : PokemonResult? = null

        if(bundle != null){
            pokemon = bundle.getSerializable(PokemonResult::class.java.toString()) as PokemonResult
        }

        title = pokemon!!.name

        // Set ViewModel
        mViewModel = ViewModelProvider(this, DetailViewModelFactory(pokemon)).get(DetailViewModel::class.java)

        // Binding ViewModel
        mBinding.viewModel = mViewModel
        mBinding.lifecycleOwner = this

    }
}