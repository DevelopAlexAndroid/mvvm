package develop.alex.android.ui.fragments.registration

import androidx.lifecycle.ViewModel
import develop.alex.android.data.repository.RegistrationRepository
import javax.inject.Inject

class RegistrationViewModel
@Inject constructor(
    var repository: RegistrationRepository
) : ViewModel() {


}