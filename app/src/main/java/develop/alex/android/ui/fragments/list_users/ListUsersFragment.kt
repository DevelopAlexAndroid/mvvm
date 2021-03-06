package develop.alex.android.ui.fragments.list_users

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DiffUtil
import com.facebook.shimmer.ShimmerFrameLayout
import develop.alex.android.R
import develop.alex.android.data.ListUsersParameters
import develop.alex.android.data.pojo.ListUserModel
import develop.alex.android.providers.Const.APP_TAG
import develop.alex.android.providers.Const.ITEM_NAME
import develop.alex.android.providers.adapters.DiffUtilCallback
import develop.alex.android.providers.adapters.UserAdapterDecoration
import develop.alex.android.providers.applyDiffAdapter
import develop.alex.android.providers.checkFastClick
import develop.alex.android.providers.replaceAdapterList
import develop.alex.android.ui.adapters.ListenerAdapter
import develop.alex.android.ui.adapters.UsersAdapter
import develop.alex.android.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_list_users.*
import kotlinx.android.synthetic.main.fragment_login.*
import javax.inject.Inject

class ListUsersFragment : BaseFragment(R.layout.fragment_list_users), ListenerAdapter {

    @Inject
    lateinit var usersAdapter: UsersAdapter

    private lateinit var viewModel: ListUsersViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel = injectViewModel()
        super.onViewCreated(view, savedInstanceState)
    }

    override fun itemClick(name: String) {
        Log.d(APP_TAG, "itemClick - $name")
        val bundle = bundleOf(
            ITEM_NAME to name
        )
        but_check.findNavController()
            .navigate(R.id.action_listUsersFragment_to_userDetailsFragment, bundle)
    }

    override fun setupObserver() {
        // setupLoadDataInLifeCycleMethod()      //load every onCreated

        //setupLoadDataInLazy()                      //load only once, but not parameter

        //  setupLoadDataInLazyMap("1")              //load only once if new key.

        // Если требуется обновление данных при каждом входе на этот экран,
        // то можно обнулить ключи LazyMap при навигации   viewModel.clearLazyMap()
        setupLoadDataInLazyMapWithMvi(ListUsersParameters("1", "2", 2))
    }

    override fun setupUI() {
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

       setupAdapter()
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
                resources.getDrawable(
                    R.drawable.divider,
                    activity?.theme
                )
            )
        )
    }

    private fun setupLoadDataInLifeCycleMethod() {
        viewModel.setupLoadDataInLifeCycleMethod()
        viewModel.users.observe(viewLifecycleOwner, Observer {
            Log.d(APP_TAG, "viewModel.users.observe - ")
            showData(it)
        })
    }

    private fun setupLoadDataInLazy() {
        viewModel.loadUsersLazy().observe(viewLifecycleOwner, Observer {
            Log.d(APP_TAG, "viewModel.loadUsers().observe - ")
            showData(it)
        })
    }

    private fun setupLoadDataInLazyMap(parameter: String) {
        viewModel.loadUsersLazyMap(parameter).observe(viewLifecycleOwner, Observer {
            Log.d(APP_TAG, "viewModel.setupLoadDataInLazyMap.observe - $parameter")
            showData(it)
        })
    }

    private fun setupLoadDataInLazyMapWithMvi(parameter: ListUsersParameters) {
        viewModel.loadUsersLazyMapWithMvi(parameter)
            .observe(viewLifecycleOwner, Observer {
                when (it) {
                    ListUsersState.Loading -> {
                        Log.d(APP_TAG, "ListUsersState.Loading")
                        showLoad()
                    }
                    is ListUsersState.ShowData -> {
                        Log.d(APP_TAG, "ListUsersState.ShowData")
                        showData(it.data)
                    }
                    ListUsersState.ErrorNetwork -> {
                        Log.d(APP_TAG, "ListUsersState.ErrorNetwork")
                        showError()
                    }
                }
            })
    }

    private fun showData(data: List<ListUserModel>) {
        list_users.visibility = View.VISIBLE
        this.applyDiffAdapter(usersAdapter, usersAdapter.users, data)
        (shimmer as ShimmerFrameLayout).stopShimmer()
        shimmer.visibility = View.GONE
        text_error.visibility = View.GONE
    }

    private fun showLoad() {
        (shimmer as ShimmerFrameLayout).startShimmer()
        shimmer.visibility = View.VISIBLE
        list_users.visibility = View.GONE
        text_error.visibility = View.GONE
    }

    private fun showError() {
        (shimmer as ShimmerFrameLayout).stopShimmer()
        shimmer.visibility = View.GONE
        text_error.visibility = View.VISIBLE
    }

}
