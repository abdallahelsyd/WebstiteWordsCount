package com.instabug.core.domain.repositories


interface IContentRepository {
    fun getWebsiteContent(callback:(success:List<String>,error:Exception?)->Unit)
}