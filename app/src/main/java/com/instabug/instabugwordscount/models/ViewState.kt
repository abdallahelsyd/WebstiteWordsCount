package com.instabug.instabugwordscount.models

abstract class ViewState {
    object Initial : ViewState()
    object Loading : ViewState()
    data class NetworkError(val error: String?) : ViewState()
    abstract class Loaded<out T>(val result: T) : ViewState()
    object Empty : ViewState()
    object UnknownError : ViewState()
}