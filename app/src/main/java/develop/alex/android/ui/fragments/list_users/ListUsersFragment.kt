package develop.alex.android.ui.fragments.list_users

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DiffUtil
import com.facebook.shimmer.ShimmerFrameLayout
import develop.alex.android.R
import develop.alex.android.data.pojo.ListUserModel
import develop.alex.android.di.Injectable
import develop.alex.android.providers.DiffUtilCallback
import develop.alex.android.providers.UserAdapterDecoration
import develop.alex.android.providers.ViewModelFactory
import develop.alex.android.ui.adapters.UsersAdapter
import kotlinx.android.synthetic.main.fragment_list_users.*
import javax.inject.Inject

//ViewModel из своей фабрики
class ListUsersFragment : Fragment(), Injectable {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    @Inject
    lateinit var usersAdapter: UsersAdapter

    private lateinit var viewModel: ListUsersViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_list_users, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel =
            ViewModelProviders.of(this, viewModelFactory).get(ListUsersViewModel::class.java)
        viewModel.onCreate()

        setupUI()
        setupObserver()
    }

    private lateinit var list: ArrayList<ListUserModel>
    private fun setupObserver() {
        viewModel.users.observe(viewLifecycleOwner, Observer {
            usersAdapter.setData(it)
            list = it as ArrayList<ListUserModel>
            (shimmer as ShimmerFrameLayout).stopShimmer()
            shimmer.visibility = View.GONE
        })
    }

    private fun setupUI() {
        list_users.itemAnimator = DefaultItemAnimator()
        list_users.adapter = usersAdapter
        //itemDecoration может быть много
        //CustomDecoration
        list_users.addItemDecoration(
            UserAdapterDecoration(resources.getDrawable(R.drawable.divider, activity?.theme)))
        //itemDecoration default
        //val divider = DividerItemDecoration(activity?.applicationContext, VERTICAL)
        //list_users.addItemDecoration(divider)

        but_check.setOnClickListener {
            var newList = ArrayList<ListUserModel>()
            newList.add(list[0])
            newList.add(list[1])
            newList.add(list[4])
            newList.add(list[5])
            newList.add(list[2])
            newList.add(list[3])
            newList.add(list[6])
            newList.add(list[8])
            newList.add(list[7])

            val diff = DiffUtil.calculateDiff(DiffUtilCallback(list, newList))
            usersAdapter.setData(newList)
            diff.dispatchUpdatesTo(usersAdapter)

            val bundle = bundleOf(
                "name" to "2"
            )
           // but_check.findNavController()
          //      .navigate(R.id.action_listUsersFragment_to_userDetailsFragment, bundle)
        }
    }

}
