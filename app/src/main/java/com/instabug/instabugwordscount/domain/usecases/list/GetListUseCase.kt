package com.instabug.instabugwordscount.domain.usecases.list

import com.instabug.instabugwordscount.domain.models.Sorting
import com.instabug.instabugwordscount.domain.repositories.ContentRepository
import com.instabug.instabugwordscount.domain.usecases.IUseCase
import getEachCount
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetListUseCase @Inject constructor(private val contentRepository: ContentRepository):
    IUseCase<GetListUseCase.Params,GetListUseCase.Result>() {
    
    override fun execute(params: Params, onResult: (result: Result) -> Unit) {
        if (params.query.isEmpty()){
            contentRepository.getWebsiteContent { success, error ->
                if (error.isNotEmpty()){
                    onResult(Result.NetworkError)
                }else{
                    val sortedWordsList=success.getEachCount().toList().sortedBy { it.second }
                    onResult(
                        when(params.sortType){
                            Sorting.Ascending ->  Result.Success(sortedWordsList.toMap())
                            Sorting.Descending -> Result.Success(sortedWordsList.reversed().toMap())
                        }
                    )
                }
            }
        }else{
            contentRepository.getWebsiteContent { success, error ->
                if (error.isNotEmpty()){
                    onResult(Result.NetworkError)
                }else {
                    val wordCount= success.getEachCount().toList().sortedBy { it.second }.toMap()[params.query]
                    val oneItemMap= hashMapOf<String,Int>()
                    oneItemMap[params.query] = wordCount?:0
                    onResult(Result.Success(oneItemMap) )
                }
            }
        }
    }
    class Params(val sortType: Sorting,val query: String="")
    sealed class Result {
        class Success(val wordsList:  Map<String, Int>) : Result()
        object NetworkError : Result()
    }


}