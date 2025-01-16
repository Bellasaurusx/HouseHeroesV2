package com.example.househeroesv2

import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


class SharedViewModel : ViewModel() {
    private val _choreCoins = MutableLiveData<Int>(1) // Start at 1 to display as 00001
    val choreCoins: LiveData<Int> get() = _choreCoins

    fun addCoins(amount: Int) {
        _choreCoins.value = (_choreCoins.value ?: 0) + amount
    }

    fun setCoins(amount: Int) {
        _choreCoins.value = amount
    }
}