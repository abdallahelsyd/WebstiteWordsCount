package com.instabug.websitewordscount.domain.repositories

import com.google.android.play.core.tasks.Task
import kotlinx.coroutines.flow.Flow


interface IContentRepository {
    fun getWebsiteContent(): Flow<String>
}