package com.instabug.websitewordscount.domain.repositories

import com.google.android.play.core.tasks.Task
import com.instabug.network.Http
import com.instabug.network.ResponseListener
import com.instabug.preferences.general.PrefsStoreImpl
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ContentRepository @Inject constructor(
    private val prefsStoreImpl: PrefsStoreImpl,
    private val http:Http.Request
):IContentRepository {
    override fun getWebsiteContent(): Flow<String> {

            http.execute(object :ResponseListener{
                override fun onResponse(res: Http.Response?) {

                }

                override fun onFailure(e: Exception?) {
                    return
                }

            } )
        return prefsStoreImpl.getString()
    }
}