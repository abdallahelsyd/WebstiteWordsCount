package com.instabug.instabugwordscount.domain.usecases.list

import com.instabug.websitewordscount.domain.repositories.ContentRepository
import com.instabug.instabugwordscount.domain.usecases.IUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetListUseCase @Inject constructor(private val contentRepository: ContentRepository):
    IUseCase<Flow<String>> {
    override fun invoke(): Flow<String> =contentRepository.getWebsiteContent()

}