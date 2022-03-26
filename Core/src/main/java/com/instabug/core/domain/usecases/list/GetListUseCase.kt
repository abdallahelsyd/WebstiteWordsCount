package com.instabug.core.domain.usecases.list

import com.instabug.core.domain.models.Sorting
import com.instabug.core.domain.models.WordItem
import com.instabug.core.domain.repositories.ContentRepository
import com.instabug.core.domain.usecases.IUseCase
import com.instabug.core.utils.getEachCount
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetListUseCase @Inject constructor(private val contentRepository: ContentRepository) :
    IUseCase<GetListUseCase.Params, GetListUseCase.Result>() {

    override fun execute(params: Params, onResult: (result: Result) -> Unit) {
        if (params.query.isEmpty()) {
            contentRepository.getWebsiteContent { success, error ->
                if (error != null) {
                    when (error) {
                        is IOException -> onResult(Result.NetworkError)
                        else -> onResult(Result.UnknownError)
                    }
                } else {
                    val sortedWordsList = success.getEachCount().toList().sortedBy { it.second }
                    onResult(
                        when (params.sortType) {
                            Sorting.Ascending -> Result.Success(
                                sortedWordsList.toMap().map {
                                    WordItem(
                                        it.key,
                                        it.value
                                    )
                                })
                            Sorting.Descending -> Result.Success(
                                sortedWordsList.reversed().toMap()
                                    .map {
                                        WordItem(
                                            it.key,
                                            it.value
                                        )
                                    })
                        }
                    )
                }
            }
        } else {
            contentRepository.getWebsiteContent { success, error ->
                if (error != null) {
                    when (error) {
                        is IOException -> onResult(Result.NetworkError)
                        else -> onResult(Result.UnknownError)
                    }
                } else {
                    val wordCount =
                        success.getEachCount().toList().sortedBy { it.second }.toMap()[params.query]
                    val oneItemMap = hashMapOf<String, Int>()
                    oneItemMap[params.query] = wordCount ?: 0
                    onResult(Result.Success(oneItemMap.map {
                        WordItem(
                            it.key,
                            it.value
                        )
                    }))
                }
            }
        }
    }

    class Params(val sortType: Sorting, val query: String = "")
    sealed class Result {
        data class Success(val wordsList: List<WordItem>) : Result()
        object NetworkError : Result()
        object UnknownError : Result()
    }


}