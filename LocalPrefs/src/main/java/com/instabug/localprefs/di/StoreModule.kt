package com.instabug.localprefs.di


import com.instabug.localprefs.general.PrefsStoreImpl
import com.instabug.localprefs.general.IPrefsStore
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class StoreModule {

 @Binds
  abstract fun bindPrefsStore(prefsStoreImpl: PrefsStoreImpl): IPrefsStore

}