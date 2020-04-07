package develop.alex.android.di.modules

import dagger.Module
import dagger.android.ContributesAndroidInjector
import develop.alex.android.ui.activity.main.MainActivity

@Module
abstract class MainActivityModule {

    @ContributesAndroidInjector(modules = [FragmentBuildersModule::class])
    abstract fun contributeMainActivity(): MainActivity

}