package com.example.pokedex.ui.pokemondetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pokedex.R
import com.example.pokedex.databinding.FragmentPokemonDetailBinding
import com.example.pokedex.navigator.PokemonDetailNavigator
import com.example.pokedex.util.view.MySnapHelper
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class PokemonDetailFragment : Fragment() {

    private lateinit var binding: FragmentPokemonDetailBinding
    private val pokemonDetailViewModel: PokemonDetailViewModel by viewModel()
    private val args: PokemonDetailFragmentArgs by navArgs()
    private var controller: PokemonDetailController? = null
    private val navigator: PokemonDetailNavigator by inject { parametersOf(parentFragment?.findNavController()) }

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
            this.onClickBack = View.OnClickListener {
                toPrev()
            }
        }

        setupController()

        lifecycle.addObserver(pokemonDetailViewModel)

        pokemonDetailViewModel.fetchData(args.id)

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        hideStatusBar()
        setupController()
        observe(pokemonDetailViewModel)
        (requireActivity() as AppCompatActivity).onBackPressedDispatcher.addCallback(
            this,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    toPrev()
                }
            })
    }

    private fun showStatusBar() {
        activity?.let {
            it.window.clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
            val decor = it.window.decorView
            decor.systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    or View.SYSTEM_UI_FLAG_IMMERSIVE)
        }
    }

    private fun hideStatusBar() {
        activity?.let {
            it.window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
            val decor = it.window.decorView
            decor.systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    or View.SYSTEM_UI_FLAG_FULLSCREEN // hide status bar
                    or View.SYSTEM_UI_FLAG_IMMERSIVE)
        }
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

    private fun toPrev() {
        hideStatusBar()
        navigator.toPrev()
    }
}