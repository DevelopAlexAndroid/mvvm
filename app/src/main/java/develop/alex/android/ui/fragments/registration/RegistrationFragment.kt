package develop.alex.android.ui.fragments.registration

import android.content.pm.ActivityInfo
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import develop.alex.android.R
import develop.alex.android.providers.Const
import develop.alex.android.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_registration.*

class RegistrationFragment : BaseFragment(R.layout.fragment_registration) {

    private lateinit var viewModel: RegistrationViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        this.viewModel = injectViewModel()
        super.onViewCreated(view, savedInstanceState)

        but_registration.setOnClickListener {
            viewModel.test()
        }

    }

    override fun setupUI() {

    }

    override fun setupObserver() {
        viewModel.userRealmListenerLiveData.observe(viewLifecycleOwner, Observer {
            Log.d(
                Const.APP_TAG,
                "setupObserver userRealmListenerLiveData: $it "
            )
        })
    }

    /**Данное решение позволяет контролировать поворот экрана в SAA на определенном фрагменте*/
    override fun onResume() {
        super.onResume()
        activity?.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_SENSOR
    }

    override fun onPause() {
        super.onPause()
        activity?.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
    }
    /**----------------------*/
}
