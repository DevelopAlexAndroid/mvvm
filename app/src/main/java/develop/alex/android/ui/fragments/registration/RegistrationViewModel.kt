package develop.alex.android.ui.fragments.registration

import androidx.lifecycle.ViewModel
import develop.alex.android.data.pojo.UserM
import develop.alex.android.data.repository.RegistrationRepository
import javax.inject.Inject

class RegistrationViewModel
@Inject constructor(
    var repository: RegistrationRepository
) : ViewModel() {

    fun test() {
        repository.testSaveRealm(UserM(0, "name", "s", "123", "pass"))

        repository.testLoadRealm()

        repository.testSaveAndCheckRealmAsync()
    }

}