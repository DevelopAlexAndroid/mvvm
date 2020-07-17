package develop.alex.android.ui.fragments.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import develop.alex.android.R
import develop.alex.android.di.modules.viewmodel.Injectable
import develop.alex.android.providers.ViewModelFactory
import develop.alex.android.providers.checkFastClick
import develop.alex.android.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_login.*
import javax.inject.Inject

class LoginFragment : BaseFragment(R.layout.fragment_login) {

    private lateinit var viewModel: LoginViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = injectViewModel()
    }

    override fun setupUI() {
        but_come_in.setOnClickListener {
            viewModel.sigIn()
            //Call to check apiKEy
        }
        but_register.setOnClickListener {
            if (checkFastClick(but_demo, R.id.loginFragment))
                but_come_in.findNavController()
                    .navigate(R.id.action_global_registrationFragment)
        }
        but_demo.setOnClickListener {
            if (checkFastClick(but_demo, R.id.loginFragment))
                but_demo.findNavController()
                    .navigate(R.id.action_loginFragment_to_listUsersFragment)
        }
    }

    override fun setupObserver() {

    }
}
