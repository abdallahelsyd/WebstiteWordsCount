package com.instabug.instabugwordscount.di


import com.instabug.instabugwordscount.domain.repositories.ContentRepository
import com.instabug.instabugwordscount.domain.repositories.IContentRepository
import com.instabug.localprefs.MyPreference
import com.instabug.localprefs.general.PrefsStoreImpl
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