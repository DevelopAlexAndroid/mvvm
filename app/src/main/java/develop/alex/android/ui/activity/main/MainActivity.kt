package develop.alex.android.ui.activity.main

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.NavHostFragment
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import develop.alex.android.R
import develop.alex.android.providers.Const.APP_TAG
import javax.inject.Inject

class MainActivity : AppCompatActivity(), HasSupportFragmentInjector {

    @Inject //Реализует механизм Dagger для поиска подходящей Factory Injectors для типа.
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewModel: MainViewModel

    override fun supportFragmentInjector():
            AndroidInjector<Fragment> = dispatchingAndroidInjector

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProviders
            .of(this, viewModelFactory)
            .get(MainViewModel::class.java)
        viewModel.onCreate()

        setupObserver()
    }

    private fun setupObserver() {
        viewModel.navigationData.observe(this, Observer { isAuth ->
            Log.d(APP_TAG, "MainActivity.navigateTo bundle authorization = $isAuth")
            //dynamic start destination in navigate components
            val navHost =
                supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment?
            val navController = navHost!!.navController
            val navInflater = navController.navInflater
            val graph = navInflater.inflate(R.navigation.nav_graph)

            if (!isAuth!!)
                graph.startDestination = R.id.loginFragment
            else
                graph.startDestination = R.id.nav_graph_users

            navController.graph = graph
        })
    }

}
