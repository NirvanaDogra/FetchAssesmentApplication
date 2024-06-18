package com.fetchassignment.displaylist.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fetchassignment.core.ui.commons.ScreenState
import com.fetchassignment.displaylist.usecase.DisplayListUseCaseImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DisplayListViewModel @Inject constructor(
    private val useCase: DisplayListUseCaseImpl
) : ViewModel() {
    var screenState by mutableStateOf<ScreenState>(ScreenState.LoadingState)
        private set

    init {
        getList()
    }

    private fun getList() {
        viewModelScope.launch {
            try {
                val list = useCase.fetchListInfo()
                screenState = ScreenState.Success(information = list)
            } catch (e: Exception) {
                screenState = ScreenState.Error(e.message)
            }
        }
    }
}