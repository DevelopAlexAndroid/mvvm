package develop.alex.android.ui.activity.main

interface MainInteractor {

    interface View {
        fun navigateTo()
    }

    interface Presenter {
        fun onCreate()
    }
}