package com.example.pokedex.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.pokedex.R
import com.example.pokedex.databinding.FragmentHomeBinding
import org.koin.android.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private val homeViewModel: HomeViewModel by viewModel()

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
        return binding.root
    }
}