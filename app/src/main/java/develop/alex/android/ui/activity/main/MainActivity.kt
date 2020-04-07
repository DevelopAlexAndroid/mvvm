package develop.alex.android.ui.activity.main

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.NavHostFragment
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import develop.alex.android.R
import develop.alex.android.providers.Const.APP_TAG
import develop.alex.android.providers.Const.USER_IS_AUTHORIZED
import develop.alex.android.ui.fragments.list_users.ListUsersViewModel
import javax.inject.Inject

class MainActivity : AppCompatActivity(), HasSupportFragmentInjector {

    @Inject //Реализует механизм Dagger для поиска подходящей Factory Injectors для типа.
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var listUsersViewModel: ListUsersViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //TEST ViewModel of dagger
        listUsersViewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(ListUsersViewModel::class.java)
        listUsersViewModel.test("MainActivity")

        //getARGS
        val s = intent.extras?.getBoolean(USER_IS_AUTHORIZED)
        Log.d(APP_TAG, "bundle $s")

        //dynamic navigate components
        val navHost =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment?
        val navController = navHost!!.navController

        val navInflater = navController.navInflater
        val graph = navInflater.inflate(R.navigation.nav_graph)

        if (s!!)
            graph.startDestination = R.id.loginFragment
        else
            graph.startDestination = R.id.nav_graph_users

        navController.graph = graph
    }

    override fun supportFragmentInjector(): AndroidInjector<Fragment> = dispatchingAndroidInjector

}