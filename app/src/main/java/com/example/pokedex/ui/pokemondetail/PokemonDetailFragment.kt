package com.example.pokedex.ui.pokemondetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pokedex.R
import com.example.pokedex.databinding.FragmentPokemonDetailBinding
import com.example.pokedex.util.view.MySnapHelper
import org.koin.android.viewmodel.ext.android.viewModel

class PokemonDetailFragment : Fragment() {

    private lateinit var binding: FragmentPokemonDetailBinding
    private val pokemonDetailViewModel: PokemonDetailViewModel by viewModel()
    private val args: PokemonDetailFragmentArgs by navArgs()
    private var controller: PokemonDetailController? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate<FragmentPokemonDetailBinding>(
            inflater,
            R.layout.fragment_pokemon_detail,
            container,
            false
        ).apply {
            this.viewModel = pokemonDetailViewModel
            this.lifecycleOwner = this@PokemonDetailFragment
        }

        setupController()

        lifecycle.addObserver(pokemonDetailViewModel)

        pokemonDetailViewModel.fetchData(args.id)

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupController()
        observe(pokemonDetailViewModel)
    }

    private fun setupController() {
        controller = PokemonDetailController()
        binding.recyclerView.apply {
            if (this.onFlingListener == null) {
                val snapHelper = MySnapHelper {}
                snapHelper.attachToRecyclerView(this)
            }
        }
        controller?.let {
            binding.recyclerView.layoutManager =
                LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
            binding.recyclerView.setController(it)
        }
    }

    private fun observe(viewModel: PokemonDetailViewModel) {
        viewModel.run {
            this.pokemon.observe(viewLifecycleOwner, Observer {
                binding.imageTypeColorHalfCircle.setImageResource(it.getTypeColorHalfCircle())
                controller?.setData(it)
            })
        }
    }
}