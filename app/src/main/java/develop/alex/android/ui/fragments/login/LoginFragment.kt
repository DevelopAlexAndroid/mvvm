package develop.alex.android.ui.fragments.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import develop.alex.android.R
import develop.alex.android.di.Injectable
import develop.alex.android.providers.ViewModelFactory
import kotlinx.android.synthetic.main.fragment_login.*
import javax.inject.Inject

//ViewModel без dataBinding
class LoginFragment : Fragment(), Injectable {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var viewModel: LoginViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        but_come_in.setOnClickListener {
            //but_come_in.findNavController().navigate(R.id.action_loginFragment_to_listUsersFragment)
            but_come_in.findNavController().navigate(R.id.action_global_registrationFragment)
        }

        viewModel = ViewModelProviders
            .of(this, viewModelFactory)
            .get(LoginViewModel::class.java)

        viewModel.sigIn()
    }
}
