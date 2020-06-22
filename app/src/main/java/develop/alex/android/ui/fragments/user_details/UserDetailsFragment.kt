package develop.alex.android.ui.fragments.user_details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import develop.alex.android.R
import develop.alex.android.di.Injectable
import develop.alex.android.providers.Const.ITEM_NAME
import develop.alex.android.providers.ViewModelFactory
import javax.inject.Inject

class UserDetailsFragment : Fragment(), Injectable {

    @Inject
    lateinit var factory: ViewModelFactory

    private lateinit var viewModel: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_user_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var s = arguments?.getString(ITEM_NAME)

        viewModel = ViewModelProviders.of(this, factory).get(UserViewModel::class.java)
        viewModel.test()

    }

}
