package com.unibeam.core.data.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStoreFile
import com.unibeam.core.common.ApplicationScope
import com.unibeam.core.common.Dispatcher
import com.unibeam.core.common.UniBeamDispatcher
import com.unibeam.core.data.settings.DataStoreUserSettingsRepository
import com.unibeam.core.data.settings.UserSettingsRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.plus
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal abstract class DataModule {

    @Binds
    abstract fun bindsUserSettingsRepository(impl: DataStoreUserSettingsRepository): UserSettingsRepository

    companion object {
        @Provides
        @Singleton
        fun providesPreferencesDataStore(
            @ApplicationContext context: Context,
            @ApplicationScope scope: CoroutineScope,
            @Dispatcher(UniBeamDispatcher.IO) ioDispatcher: CoroutineDispatcher,
        ): DataStore<Preferences> = PreferenceDataStoreFactory.create(
            scope = scope + ioDispatcher,
            produceFile = { context.preferencesDataStoreFile("user_settings") },
        )
    }
}
