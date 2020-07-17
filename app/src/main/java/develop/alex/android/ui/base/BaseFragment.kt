package develop.alex.android.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import develop.alex.android.di.modules.viewmodel.Injectable
import develop.alex.android.providers.ViewModelFactory
import javax.inject.Inject

abstract class BaseFragment(var layout: Int) : Fragment(), Injectable {

    @Inject
    lateinit var factory: ViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
        setupObserver()
    }

    abstract fun setupUI()
    abstract fun setupObserver()
    open fun ifYouNeededOverride(){}

    inline fun <reified T : ViewModel> injectViewModel(): T =
        ViewModelProviders
            .of(this, factory)
            .get(T::class.java)

}