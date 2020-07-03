package develop.alex.android.di.modules

import android.content.Context
import dagger.Module
import dagger.Provides
import develop.alex.android.App
import develop.alex.android.di.modules.viewmodel.ViewModelsModule
import develop.alex.android.providers.SharedPreferencesProvider
import javax.inject.Singleton

@Module(includes = [ViewModelsModule::class])
class AppModule {

    @Singleton
    @Provides
    fun provideContext(app: App): Context = app

    @Singleton
    @Provides
    fun provideSharedPreferences(context: Context):
            SharedPreferencesProvider = SharedPreferencesProvider(context)

}