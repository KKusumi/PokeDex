package com.example.pokedex.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.pokedex.common.ext.isShow
import com.example.pokedex.common.navigator.HomeNavigator
import com.example.pokedex.home.databinding.FragmentHomeBinding
import com.example.pokedex.shared.ext.changeStatusBarColor
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class HomeFragment : Fragment(R.layout.fragment_home) {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val viewModel: HomeViewModel by viewModel()
    private val navigator: HomeNavigator by inject { parametersOf(parentFragment?.findNavController()) }
    private var controller: HomeController = HomeController(
        onClickItem = {
            navigator.toPokemonDetail(it)
        }
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentHomeBinding.bind(view).apply {
            this.viewModel = viewModel
            this.lifecycleOwner = this@HomeFragment
        }
        lifecycle.addObserver(viewModel)

        observe(viewModel)
        setupController()
        setupStatusBar()
    }

    override fun onDestroyView() {
        binding.recyclerView.clear()
        binding.recyclerView.recycledViewPool.clear()
        _binding = null
        super.onDestroyView()
    }

    private fun setupController() {
        binding.recyclerView.setController(controller)
    }

    private fun setupStatusBar() {
        activity?.changeStatusBarColor("#ffffff")
    }

    private fun observe(viewModel:HomeViewModel) {
        viewModel.run {
            this.uiModel.observe(viewLifecycleOwner) { uiModel ->
                binding.progressBar.isShow = uiModel.isLoading
                controller.setData(uiModel.pokemonListView)
                uiModel.error?.let {
                    // エラー時処理
                }
            }
        }
    }
}