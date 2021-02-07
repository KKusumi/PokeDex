package com.example.pokedex.common.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import com.example.pokedex.common.R
import com.example.pokedex.common.databinding.LayoutRetryBinding
import com.example.pokedex.model.UiState

class RetryView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private var binding: LayoutRetryBinding = DataBindingUtil.inflate(
        LayoutInflater.from(context),
        R.layout.layout_retry,
        this,
        true
    )

    fun setState(uiState: UiState) {
        binding.uiState = uiState
    }

    fun setOnClickRetryListener(onClickRetry: () -> Unit) {
        binding.onClickRetry = OnClickListener {
            onClickRetry.invoke()
        }
    }
}

object RetryViewBindings {

    @BindingAdapter("app:uiState") // app:uiStateという属性名で設定できるようになる。
    @JvmStatic
    fun setState(retryView: RetryView, uiState: UiState?) { // 第一引数：どのクラスのViewに適用するか。 第二引数：受け取る値の型
        uiState?.let {
            retryView.setState(it)
        }
    }

    @BindingAdapter("app:onRetry")
    @JvmStatic
    fun setRetry(retryView: RetryView, onClickRetry: (() -> Unit)?) {
        retryView.setOnClickRetryListener {
            onClickRetry?.invoke()
        }
    }
}
