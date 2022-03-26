package com.instabug.instabugwordscount.domain.usecases

interface IUseCase<ReturnType> {
    fun invoke(): ReturnType
}