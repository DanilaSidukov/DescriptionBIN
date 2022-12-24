package com.sidukov.descriptionbin.descriptionbin.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sidukov.descriptionbin.descriptionbin.data.CardBINRepository
import com.sidukov.descriptionbin.descriptionbin.domain.DataBIN
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class CardBINViewModel(
    private val repository: CardBINRepository
) : ViewModel() {

    private val _dataBIN = MutableSharedFlow <DataBIN>()
    var dataBIN = _dataBIN.asSharedFlow()

    private val _error = MutableSharedFlow<String>()
    var error = _error.asSharedFlow()

    fun requestBinData(binString: String) {
        viewModelScope.launch {
//            with(repository.getData(binString)){
//                _dataBIN.emit(this)
//            }
            with(repository.getData(binString)) {
                if (data == null) {
                    _error.emit(error ?: "Unknown error!")
                } else {
                    _dataBIN.emit(data)
                }
            }
        }
    }
}