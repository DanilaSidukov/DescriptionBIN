package com.sidukov.descriptionbin.descriptionbin.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sidukov.descriptionbin.descriptionbin.data.CardBINRepository
import com.sidukov.descriptionbin.descriptionbin.domain.DataBIN
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CardBINViewModel(
    private val repository: CardBINRepository
) : ViewModel() {

    private val _dataBIN = MutableStateFlow<List<DataBIN>>(emptyList())
    var dataBIN = _dataBIN.asStateFlow()

init {
    viewModelScope.launch {
        val value = repository.getData()

        if (value.isEmpty()) return@launch
        _dataBIN.emit(value)
    }
}
}