package com.example.pokemon_detail

import android.os.Bundle
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pokedex.common.navigator.PokemonDetailNavigator
import com.example.pokedex.common.view.MySnapHelper
import com.example.pokedex.shared.ext.changeStatusBarColor
import com.example.pokemon_detail.databinding.FragmentPokemonDetailBinding
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class PokemonDetailFragment : Fragment(R.layout.fragment_pokemon_detail) {

    private var _binding: FragmentPokemonDetailBinding? = null
    private val binding get() = _binding!!
    private val pokemonDetailViewModel: PokemonDetailViewModel by viewModel()
    private val args: PokemonDetailFragmentArgs by navArgs()
    private var controller: PokemonDetailController? = null
    private val navigator: PokemonDetailNavigator by inject { parametersOf(parentFragment?.findNavController()) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentPokemonDetailBinding.bind(view).apply {
            this.viewModel = pokemonDetailViewModel
            this.lifecycleOwner = this@PokemonDetailFragment
            this.onClickBack = View.OnClickListener {
                toPrev()
            }
        }
        lifecycle.addObserver(pokemonDetailViewModel)

        setupController()

        pokemonDetailViewModel.fetchData(args.id)
        observe(pokemonDetailViewModel)
        (requireActivity() as AppCompatActivity).onBackPressedDispatcher.addCallback(
            viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    toPrev()
                }
            })
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
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
            this.pokemonDetailView.observe(viewLifecycleOwner) {
                controller?.setData(it)
                activity?.changeStatusBarColor(it.types[0].type.colorCode)
            }
        }
    }

    private fun toPrev() {
        navigator.toPrev()
    }
}