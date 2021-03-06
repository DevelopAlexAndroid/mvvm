package develop.alex.android.di

import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import develop.alex.android.App
import develop.alex.android.di.modules.ApiModule
import develop.alex.android.di.modules.AppModule
import develop.alex.android.di.modules.DataModule
import develop.alex.android.di.modules.ActivityModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        AppModule::class,
        DataModule::class,
        ApiModule::class,
        ActivityModule::class
    ]
)
interface AppComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(app: App): Builder
        fun build(): AppComponent
    }
     fun inject(app: App)
}