package develop.alex.android.ui.fragments.registration

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders

import develop.alex.android.R
import develop.alex.android.di.modules.viewmodel.Injectable
import develop.alex.android.providers.ViewModelFactory
import kotlinx.android.synthetic.main.fragment_registration.*
import javax.inject.Inject

class RegistrationFragment : Fragment(),
    Injectable {

    @Inject
    lateinit var factory: ViewModelFactory

    private lateinit var viewModel: RegistrationViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_registration, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProviders
            .of(this, factory)
            .get(RegistrationViewModel::class.java)

        but_registration.setOnClickListener {
            viewModel.test()
        }

    }

}
