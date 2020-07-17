package develop.alex.android.ui.fragments.user_details

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import develop.alex.android.R
import develop.alex.android.di.modules.viewmodel.Injectable
import develop.alex.android.providers.Const.APP_TAG
import develop.alex.android.providers.Const.ITEM_NAME
import develop.alex.android.providers.ViewModelFactory
import develop.alex.android.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_list_users.*
import kotlinx.android.synthetic.main.fragment_user_details.*
import javax.inject.Inject

class UserDetailsFragment : BaseFragment(R.layout.fragment_user_details) {

    private lateinit var viewModel: UserViewModel
    private lateinit var name: String

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel = injectViewModel()
        super.onViewCreated(view, savedInstanceState)

        name = arguments?.getString(ITEM_NAME) ?: ""
        //navigate
        textView.findNavController()
            .navigate(R.id.action_userDetailsFragment_to_registrationFragment2)

    }

    override fun setupUI() {
        textView.setOnClickListener {
            viewModel.getUser(name)
        }
        imageView.setOnClickListener {
            viewModel.updateUsersLiveData(name)
        }
        textView2.setOnClickListener {
            viewModel.postName(name)
        }
    }

    override fun setupObserver() {
        viewModel.userRepData.observe(viewLifecycleOwner, Observer {
            Log.d(APP_TAG, "Observer liveDataRepos")
            textView.text = "userRepData = ${it.name}"
        })
        viewModel.userVMData.observe(viewLifecycleOwner, Observer {
            Log.d(APP_TAG, "Observer liveDataVM")
            textView.text = "userVMData = ${it.name}"
        })
        viewModel.userLiveData.observe(viewLifecycleOwner, Observer {
            Log.d(APP_TAG, "Observer liveDataTransform")
            textView.text = "userLiveData = ${it.name}"
        })
    }

}
