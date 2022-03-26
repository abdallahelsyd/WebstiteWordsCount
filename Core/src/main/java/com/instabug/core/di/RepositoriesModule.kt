package com.instabug.core.di


import com.instabug.core.domain.repositories.ContentRepository
import com.instabug.core.domain.repositories.IContentRepository
import com.instabug.localprefs.MyPreference

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
        myPreference: MyPreference,
    ): IContentRepository =
        ContentRepository(myPreference)
}