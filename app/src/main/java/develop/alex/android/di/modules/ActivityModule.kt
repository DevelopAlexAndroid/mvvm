package develop.alex.android.di.modules

import dagger.Module
import dagger.android.ContributesAndroidInjector
import develop.alex.android.di.modules.fragments.FragmentsModule
import develop.alex.android.ui.activity.main.MainActivity
import develop.alex.android.ui.activity.splash.SplashActivity

@Module
abstract class ActivityModule {

    @ContributesAndroidInjector
    abstract fun contributeSplashActivity(): SplashActivity

    @ContributesAndroidInjector(modules = [FragmentsModule::class])
    abstract fun contributeMainActivity(): MainActivity

}