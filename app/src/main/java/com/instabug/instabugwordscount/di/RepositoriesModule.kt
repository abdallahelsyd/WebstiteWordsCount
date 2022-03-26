package com.instabug.websitewordscount.di

import com.instabug.network.Http
import com.instabug.preferences.general.PrefsStoreImpl
import com.instabug.websitewordscount.domain.repositories.ContentRepository
import com.instabug.websitewordscount.domain.repositories.IContentRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object RepositoriesModule {

    @Provides
    @Singleton
    fun provideListRepository(
        prefsStoreImpl: PrefsStoreImpl,
        http: Http.Request,
    ): IContentRepository =
        ContentRepository(prefsStoreImpl, http)
}