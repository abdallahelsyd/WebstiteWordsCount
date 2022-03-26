package com.instabug.instabugwordscount.domain.usecases


abstract class IUseCase<in Params, Result> {

    /**
     * Use case should contain only one public function execute() which will execute the useCase
     */
    abstract fun execute(params: Params, onResult: (result: Result) -> Unit)
}