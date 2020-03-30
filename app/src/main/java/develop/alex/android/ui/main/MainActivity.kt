package develop.alex.android.ui.main

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import develop.alex.android.R
import develop.alex.android.ui.Const.APP_TAG
import develop.alex.android.ui.Const.USER_IS_AUTHORIZED


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val s = intent.extras?.getBoolean(USER_IS_AUTHORIZED)
        Log.d(APP_TAG, "bundle $s")

        val navHost = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment?
        val navController = navHost!!.navController

        val navInflater = navController.navInflater
        val graph = navInflater.inflate(R.navigation.nav_graph)

        if (!s!!)
            graph.startDestination = R.id.loginFragment
        else
            graph.startDestination = R.id.nav_graph_users

        navController.graph = graph
    }


}
