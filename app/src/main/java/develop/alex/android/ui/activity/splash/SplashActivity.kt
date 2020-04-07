package develop.alex.android.ui.activity.splash

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import develop.alex.android.R
import develop.alex.android.providers.Const.USER_IS_AUTHORIZED
import develop.alex.android.ui.activity.main.MainActivity
import kotlinx.android.synthetic.main.activity_splash.*


class SplashActivity : AppCompatActivity() {

    //private lateinit var splashDataBinding: ActivitySplashBinding
    private lateinit var viewModel: SplashViewModel
    private var isAuthorized = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //splashDataBinding = DataBindingUtil.
        setContentView( R.layout.activity_splash)
     //   splashDataBinding.apply {
     //       viewModel = ViewModelProviders.of(this@SplashActivity).get(SplashViewModel::class.java)
      //      lifecycleOwner = this@SplashActivity
      //  }
     //   splashDataBinding.viewModel?.onCreate()


        viewModel = ViewModelProviders.of(this).get(SplashViewModel::class.java)
        viewModel.onCreate()

        setupObserver()

        button.setOnClickListener {
            //Заглушка на ошибку авторизации
            isAuthorized = true
        }
    }

    private fun setupObserver() {
        viewModel.isAuthorized.observe(this, Observer {
            val intent = Intent(this, MainActivity::class.java)
            val bundle = bundleOf(USER_IS_AUTHORIZED to isAuthorized)
            intent.putExtras(bundle)
            startActivity(intent)
            finish()
        })
    }
}
