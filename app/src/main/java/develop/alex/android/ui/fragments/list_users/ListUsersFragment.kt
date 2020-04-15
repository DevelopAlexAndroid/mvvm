package develop.alex.android.ui.fragments.list_users

import android.os.Bundle
import android.util.Log
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
import develop.alex.android.di.Injectable
import develop.alex.android.providers.Const
import develop.alex.android.providers.Const.ITEM_NAME
import develop.alex.android.providers.ViewModelFactory
import develop.alex.android.providers.adapters.DiffUtilCallback
import develop.alex.android.providers.adapters.UserAdapterDecoration
import develop.alex.android.providers.applyDiffAdapter
import develop.alex.android.providers.replaceAdapterList
import develop.alex.android.ui.adapters.ListenerAdapter
import develop.alex.android.ui.adapters.UsersAdapter
import kotlinx.android.synthetic.main.fragment_list_users.*
import javax.inject.Inject

//ViewModel из своей фабрики
class ListUsersFragment : Fragment(), Injectable, ListenerAdapter {

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
        viewModel = ViewModelProviders
            .of(this, viewModelFactory)
            .get(ListUsersViewModel::class.java)


        setupUI()
        setupAdapter()

        // setupLoadDataInLifeCycleMethod()     //load every onCreated
        //setupLoadDataInLazy()                 //load only once, but not parameter
        setupLoadDataInLazyMap("1")             //load only once if new key
    }

    override fun itemClick(name: String) {
        Log.d(Const.APP_TAG, "itemClick - $name")
        val bundle = bundleOf(
            ITEM_NAME to name
        )
        but_check.findNavController()
            .navigate(R.id.action_listUsersFragment_to_userDetailsFragment, bundle)

    }

    private fun setupUI() {
        but_check.setOnClickListener {
        }
        text_update_list.setOnClickListener {
            val newList = this.replaceAdapterList(usersAdapter.users)
            val diff = DiffUtil.calculateDiff(
                DiffUtilCallback(usersAdapter.users, newList)
            )
            usersAdapter.setData(newList)
            diff.dispatchUpdatesTo(usersAdapter)
        }
    }

    private fun setupAdapter() {
        list_users.itemAnimator = DefaultItemAnimator()
        list_users.adapter = usersAdapter

        //itemDecoration может быть много
        //    itemDecoration default
        //val divider = DividerItemDecoration(activity?.applicationContext, VERTICAL)
        //list_users.addItemDecoration(divider)
        //    CustomDecoration
        list_users.addItemDecoration(
            UserAdapterDecoration(
                resources.getDrawable(R.drawable.divider, activity?.theme)
            )
        )
    }


    private fun setupLoadDataInLifeCycleMethod() {
        viewModel.onCreate()
        viewModel.users.observe(viewLifecycleOwner, Observer {
            Log.d(Const.APP_TAG, "viewModel.users.observe - ")
            usersAdapter.setData(it)
            (shimmer as ShimmerFrameLayout).stopShimmer()
            shimmer.visibility = View.GONE
        })
    }

    private fun setupLoadDataInLazy() {
        viewModel.loadUsersLazy().observe(viewLifecycleOwner, Observer {
            Log.d(Const.APP_TAG, "viewModel.loadUsers().observe - ")
            this.applyDiffAdapter(usersAdapter, usersAdapter.users, it)
            (shimmer as ShimmerFrameLayout).stopShimmer()
            shimmer.visibility = View.GONE
        })
    }

    private fun setupLoadDataInLazyMap(parameter: String) {
        viewModel.loadUsersLazyMap(parameter).observe(viewLifecycleOwner, Observer {
            Log.d(Const.APP_TAG, "viewModel.setupLoadDataInLazyMap(parameter: String).observe - $parameter")
            this.applyDiffAdapter(usersAdapter, usersAdapter.users, it)
            (shimmer as ShimmerFrameLayout).stopShimmer()
            shimmer.visibility = View.GONE
        })
    }


}
