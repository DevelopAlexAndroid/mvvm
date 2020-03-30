package develop.alex.android.ui.fragments.list_users

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.findNavController

import develop.alex.android.R
import kotlinx.android.synthetic.main.fragment_list_users.*

class ListUsersFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_list_users, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        but_check.setOnClickListener {
            var bundle = bundleOf(
                "name" to "2"
            )
            but_check.findNavController().navigate(R.id.action_listUsersFragment_to_userDetailsFragment, bundle)
        }
    }

}
