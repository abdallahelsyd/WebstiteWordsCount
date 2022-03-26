package com.instabug.instabugwordscount.ui
import com.instabug.instabugwordscount.models.ViewState
import com.instabug.core.domain.models.WordItem

sealed class WordsListViewState() : ViewState() {
    data class OnSuccess(val data: List<WordItem>) : Loaded<List<WordItem>>(data)
}