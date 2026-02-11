package com.gw.myearnings.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gw.myearnings.repository.EarnedCashRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

//class SettingsViewModel : ViewModel() {
//
//    private val _selectedCurrency = MutableStateFlow("KES")
//    val selectedCurrency: StateFlow<String> = _selectedCurrency
//
//    fun setCurrency(currency: String) {
//        _selectedCurrency.value = currency
//        // Later you can save to DataStore or Room
//    }
//}


class SettingsViewModel(
    private val repository: EarnedCashRepository
) : ViewModel() {

    private val _selectedCurrency = MutableStateFlow("KES")
    val selectedCurrency: StateFlow<String> = _selectedCurrency

    init {
        loadCurrency()
    }

    private fun loadCurrency() {
        viewModelScope.launch {
            _selectedCurrency.value = repository.getCurrency()
        }
    }

    fun setCurrency(currency: String) {
        viewModelScope.launch {
            repository.saveCurrency(currency)
            _selectedCurrency.value = currency
        }
    }
}
