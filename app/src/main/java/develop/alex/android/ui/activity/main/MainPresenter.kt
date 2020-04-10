package develop.alex.android.ui.activity.main

import develop.alex.android.data.repository.MainRepository
import javax.inject.Inject

class MainPresenter
@Inject constructor(
    private var view: MainInteractor.View,
    private val repository: MainRepository
): MainInteractor.Presenter {

    override fun onCreate() {
        view.navigateTo()
    }

}