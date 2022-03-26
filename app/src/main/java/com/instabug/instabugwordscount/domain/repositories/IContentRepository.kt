package com.instabug.instabugwordscount.domain.repositories

import com.google.android.play.core.tasks.Task
import kotlinx.coroutines.flow.Flow


interface IContentRepository {
    fun getWebsiteContent(callback:(success:List<String>,error:Exception?)->Unit)
}