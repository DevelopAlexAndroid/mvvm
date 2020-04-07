package develop.alex.android.ui.fragments.list_users

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import develop.alex.android.R
import develop.alex.android.di.Injectable
import develop.alex.android.providers.ViewModelFactory
import kotlinx.android.synthetic.main.fragment_list_users.*
import javax.inject.Inject

//ViewModel из своей фабрики
class ListUsersFragment : Fragment(), Injectable {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private lateinit var viewModel: ListUsersViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_list_users, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(ListUsersViewModel::class.java)
        viewModel.test("ListUsersFragment")

        but_check.setOnClickListener {
            var bundle = bundleOf(
                "name" to "2"
            )
            but_check.findNavController()
                .navigate(R.id.action_listUsersFragment_to_userDetailsFragment, bundle)
        }
    }

}
