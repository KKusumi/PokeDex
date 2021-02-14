package com.example.pokedex.common.ext

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData

inline fun <T : Any, LIVE1 : Any> combine(
    initialValue: T,
    liveData1: LiveData<LIVE1>,
    crossinline block: (T, LIVE1) -> T
): MutableLiveData<T> {

    return MediatorLiveData<T>().apply {
        value = initialValue
        addSource(liveData1) {
            val currentValue = value
            val liveData1Value = liveData1.value
            if (currentValue != null && liveData1Value != null) {
                value = block(currentValue, liveData1Value)
            }
        }
    }
}