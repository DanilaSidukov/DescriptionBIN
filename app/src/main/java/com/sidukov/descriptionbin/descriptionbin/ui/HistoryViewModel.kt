package com.sidukov.descriptionbin.descriptionbin.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sidukov.descriptionbin.descriptionbin.data.CardBINRepository
import com.sidukov.descriptionbin.descriptionbin.data.local.EntityHistoryBIN
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HistoryViewModel (
    private val repository: CardBINRepository
): ViewModel() {

    private val _historyData = MutableSharedFlow<List<EntityHistoryBIN>>()
    var historyData = _historyData.asSharedFlow()

    private val _shouldShowNoHistoryText = MutableStateFlow(true)
    var shouldShowNoHistoryText = _shouldShowNoHistoryText.asStateFlow()

    init {
        requestHistory()
    }

    fun requestHistory(){
        viewModelScope.launch{
            with(repository.getBinHistory()){
                if (this.isNotEmpty()){
                    _historyData.emit(this)
                    _shouldShowNoHistoryText.emit(false)
                } else {
                    _shouldShowNoHistoryText.emit(true)
                }
            }
        }
    }

    fun deleteHistory(){
        viewModelScope.launch{
            with(repository.deleteBinHistory()){
            }
        }
    }
}