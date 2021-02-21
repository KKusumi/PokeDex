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
import com.example.pokedex.common.ext.isShow
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
    private val args: PokemonDetailFragmentArgs by navArgs()
    private val pokemonDetailViewModel: PokemonDetailViewModel by viewModel { parametersOf(args.id) }
    private var controller = PokemonDetailController()
    private val navigator: PokemonDetailNavigator by inject { parametersOf(parentFragment?.findNavController()) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentPokemonDetailBinding.bind(view).apply {
            this.lifecycleOwner = this@PokemonDetailFragment
            this.onClickBack = View.OnClickListener {
                navigator.toPrev()
            }
        }
        lifecycle.addObserver(pokemonDetailViewModel)

        setupController()

        observe(pokemonDetailViewModel)
        (requireActivity() as AppCompatActivity).onBackPressedDispatcher.addCallback(
            viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    navigator.toPrev()
                }
            })
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    private fun setupController() {
        binding.recyclerView.apply {
            if (this.onFlingListener == null) {
                val snapHelper = MySnapHelper {}
                snapHelper.attachToRecyclerView(this)
            }
        }
        binding.recyclerView.setController(controller)
    }

    private fun observe(viewModel: PokemonDetailViewModel) {
        viewModel.run {
            this.uiModel.observe(viewLifecycleOwner) { uiModel ->
                binding.progressBar.isShow = uiModel.isLoading
                binding.pokemonDetailView = uiModel.pokemonDetailView
                activity?.changeStatusBarColor(uiModel.pokemonDetailView.primaryColorCode)
                controller.setData(uiModel.pokemonDetailView)
                uiModel.error?.let {
                    // エラー時処理
                }
            }
        }
    }
}