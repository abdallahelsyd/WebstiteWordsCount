package com.instabug.instabugwordscount.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.instabug.instabugwordscount.domain.models.Sorting
import com.instabug.instabugwordscount.domain.models.ViewState
import com.instabug.instabugwordscount.domain.usecases.list.GetListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(private val getListUseCase: GetListUseCase) :
    ViewModel() {


    var dataSaved = MutableLiveData<ViewState>()

    init {
        setState(ViewState.Loading)
        getListUseCase.execute(GetListUseCase.Params(Sorting.Descending, ""), onResult = {
            when (it) {
                GetListUseCase.Result.NetworkError -> setState(ViewState.Error("NetworkError"))
                is GetListUseCase.Result.Success -> setState(WordsListViewState.OnSuccess(it.wordsList))
            }
        })
    }

    private fun setState(state: ViewState) {
        viewModelScope.launch {
            dataSaved.value = state
        }
    }

    fun getData(sorting: Sorting, query: String) {
        setState(ViewState.Loading)
        getListUseCase.execute(GetListUseCase.Params(sorting, query), onResult = {
            when (it) {
                GetListUseCase.Result.NetworkError -> setState(ViewState.Error("NetworkError"))
                is GetListUseCase.Result.Success -> setState(WordsListViewState.OnSuccess(it.wordsList))
            }
        })
    }


}