package develop.alex.android.providers

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject
import javax.inject.Provider
import javax.inject.Singleton

@Suppress("UNCHECKED_CAST")
class ViewModelFactory<T : ViewModel>
@Inject
constructor(private val viewModelsProvider: Provider<T>) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return viewModelsProvider.get() as T
    }
}