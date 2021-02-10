package com.example.pokedex.home

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.pokedex.common.navigator.HomeNavigator
import com.example.pokedex.home.databinding.FragmentHomeBinding
import com.example.pokedex.common.util.EventObserver
import com.example.pokedex.shared.ext.changeStatusBarColor
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class HomeFragment : Fragment(R.layout.fragment_home) {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val viewModel: HomeViewModel by viewModel()
    private var controller: HomeController? = null
    private val navigator: HomeNavigator by inject { parametersOf(parentFragment?.findNavController()) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentHomeBinding.bind(view).apply {
            this.viewModel = viewModel

            // これがないと、ViewModelのLiveDataにデータを設定してもレイアウトに反映されない。
            this.lifecycleOwner = this@HomeFragment
        }
        // ViewModel側でFragmentのライフサイクルに応じて呼ばれるメソッドを使えるようになる。
        lifecycle.addObserver(viewModel)

        observe(viewModel)
        setupController()

        activity?.changeStatusBarColor("#ffffff")
    }

    override fun onDestroyView() {
        binding.recyclerView.clear()
        binding.recyclerView.recycledViewPool.clear()
        _binding = null
        super.onDestroyView()
    }

    private fun setupController() {
        controller = HomeController(
            onClickItem = {
                navigator.toPokemonDetail(it)
            }
        )
        controller?.let { it ->
            binding.recyclerView.setController(it)
        }
    }

    private fun observe(viewModel:HomeViewModel) {
        viewModel.run {
            this.pokemonListResponse.observe(viewLifecycleOwner, {
                controller?.setData(it)
            })
            this.showErrorCommand.observe(viewLifecycleOwner, EventObserver { message ->
                context?.let { context ->
                    AlertDialog.Builder(context)
                        .setTitle(R.string.error_alert_title)
                        .setMessage(message)
                        .setPositiveButton(R.string.ok) { _, _ ->
                            // todo: あとあとリトライとかさせる。
                        }
                        .setNegativeButton(R.string.cancel, null)
                }
            })
        }
    }
}