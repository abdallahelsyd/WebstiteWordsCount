package com.instabug.instabugwordscount.ui
import com.instabug.instabugwordscount.domain.models.ViewState
import com.instabug.instabugwordscount.domain.models.WordItem

sealed class WordsListViewState() : ViewState() {
    data class OnSuccess(val data: List<WordItem>) : Loaded<List<WordItem>>(data)
}