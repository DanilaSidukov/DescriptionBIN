package com.sidukov.descriptionbin.descriptionbin.ui

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sidukov.descriptionbin.descriptionbin.data.CardBinRepository
import com.sidukov.descriptionbin.descriptionbin.domain.DataBin
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class CardBinViewModel(
    private val repository: CardBinRepository
) : ViewModel() {

    private val _dataBin = MutableSharedFlow<DataBin>()
    var dataBin = _dataBin.asSharedFlow()

    private val _error = MutableSharedFlow<String>()
    var error = _error.asSharedFlow()

    @RequiresApi(Build.VERSION_CODES.O)
    fun requestBinData(binString: String) {
        viewModelScope.launch(Dispatchers.IO) {
            with(repository.getData(binString)) {
                if (data == null) {
                    _error.emit(error ?: "Unknown error!")
                } else {
                    _dataBin.emit(data)
                }
            }
        }
    }
}