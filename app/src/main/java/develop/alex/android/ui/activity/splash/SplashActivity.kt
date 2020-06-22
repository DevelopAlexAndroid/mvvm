package develop.alex.android.ui.activity.splash

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import develop.alex.android.R
import develop.alex.android.di.Injectable
import develop.alex.android.providers.Const.USER_IS_AUTHORIZED
import develop.alex.android.providers.ViewModelFactory
import develop.alex.android.ui.activity.main.MainActivity
import kotlinx.android.synthetic.main.activity_splash.*
import javax.inject.Inject

class SplashActivity : FragmentActivity(), Injectable {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    //private lateinit var splashDataBinding: ActivitySplashBinding
    private lateinit var viewModel: SplashViewModel
    private var isAuthorized = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        /** Привязка DataBinding*/
        //splashDataBinding = DataBindingUtil.
        //   splashDataBinding.apply {
        //       viewModel = ViewModelProviders.of(this@SplashActivity).get(SplashViewModel::class.java)
        //      lifecycleOwner = this@SplashActivity
        //  }
        //   splashDataBinding.viewModel?.onCreate()
        /** Ручное внедрение ViewModel*/
       // viewModel = ViewModelProviders.of(this).get(SplashViewModel::class.java)
        /** Внедрение спомощью Dagger*/
        viewModel = ViewModelProviders
            .of(this, viewModelFactory)
            .get(SplashViewModel::class.java)

        viewModel.onCreate()

        setupObserver()
    }

    private fun setupObserver() {
        viewModel.isAuthorized.observe(this, Observer {
            val intent = Intent(this, MainActivity::class.java)
            //bundle
            //val bundle = bundleOf(USER_IS_AUTHORIZED to isAuthorized)
            //intent.putExtras(bundle)
            startActivity(intent)
            finish()
        })
    }
}
