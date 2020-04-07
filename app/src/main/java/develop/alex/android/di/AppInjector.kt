package develop.alex.android.di

import android.app.Activity
import android.app.Application
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import dagger.android.AndroidInjection
import dagger.android.support.AndroidSupportInjection
import dagger.android.support.HasSupportFragmentInjector
import develop.alex.android.App

/**
 * Пустой пустой интерфейс, который провацирует автоматический inject activity или fragment
 **/
interface Injectable

//unchecked cast for ViewModel
@Suppress("unchecked")
object AppInjector {
    fun init(app: App) {
        // Here we initialize Dagger. DaggerAppComponent is auto-generated from AppComponent.
        DaggerAppComponent
            .builder()
            .application(app)
            .build()
            .inject(app)

        app.registerActivityLifecycleCallbacks(
            object : Application.ActivityLifecycleCallbacks {
                override fun onActivityPaused(activity: Activity) {}
                override fun onActivityResumed(activity: Activity) {}
                override fun onActivityStarted(activity: Activity) {}
                override fun onActivityDestroyed(activity: Activity) {}
                override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle?) {}
                override fun onActivityStopped(activity: Activity) {}
                override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
                    handleActivity(activity)
                }
            }
        )
    }

    private fun handleActivity(activity: Activity) {
        if (activity is HasSupportFragmentInjector || activity is Injectable) {
            // Calling inject() method will cause Dagger to locate the singletons in the dependency graph to try to find a matching return type.
            // If it finds one, it assigns the references to the respective fields.
            AndroidInjection.inject(activity)
        }
        if (activity is FragmentActivity) {
            activity.supportFragmentManager.registerFragmentLifecycleCallbacks(object :
                FragmentManager.FragmentLifecycleCallbacks() {
                override fun onFragmentCreated(
                    fragmentManager: FragmentManager,
                    fragment: Fragment,
                    savedInstanceState: Bundle?
                ) {
                    if (fragment is Injectable) {
                        AndroidSupportInjection.inject(fragment)
                    }
                }
            }, true)
        }
    }
}