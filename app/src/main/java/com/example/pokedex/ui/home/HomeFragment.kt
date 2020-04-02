package com.example.pokedex.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.pokedex.R
import com.example.pokedex.databinding.FragmentHomeBinding
import com.example.pokedex.util.EventObserver
import org.koin.android.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private val homeViewModel: HomeViewModel by viewModel()
    private var controller: HomeController? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate<FragmentHomeBinding>(
            inflater,
            R.layout.fragment_home,
            container,
            false
        ).apply {
            this.viewModel = homeViewModel

            /**
             * これがないと、ViewModelのLiveDataにデータを設定してもレイアウトに反映されない。
             * よく忘れがちなので注意。
             */
            this.lifecycleOwner = this@HomeFragment
        }

        /**
         * ViewModel側で、Fragmentのライフサイクルに応じて呼ばれるメソッドを使えるようになる。
         */
        lifecycle.addObserver(homeViewModel)

        homeViewModel.fetchData()

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        observeState(homeViewModel)
        observeEvent(homeViewModel)
        setupController()
    }

    private fun setupController() {
        controller = HomeController()
        controller?.let { controller ->
            binding.recyclerView.setController(controller)
        }
    }

    private fun observeState(viewModel: HomeViewModel) {
        viewModel.run {
            this.pokemonListResponse.observe(viewLifecycleOwner, Observer {
                // todo
            })
        }
    }

    private fun observeEvent(viewModel: HomeViewModel) {
        viewModel.run {
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