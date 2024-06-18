package com.fetchassignment.core.ui.commons

sealed class ScreenState {
    data object LoadingState : ScreenState()
    data class Success(val information: Any) : ScreenState()
    data class Error(val error: String?) : ScreenState()
}