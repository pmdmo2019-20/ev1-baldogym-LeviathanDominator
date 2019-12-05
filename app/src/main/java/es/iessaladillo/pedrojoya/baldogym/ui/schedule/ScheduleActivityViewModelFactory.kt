package es.iessaladillo.pedrojoya.baldogym.ui.schedule

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import es.iessaladillo.pedrojoya.baldogym.data.LocalRepository

@Suppress("UNCHECKED_CAST")
class ScheduleActivityViewModelFactory(private val localRepository: LocalRepository, private val application: Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ScheduleActivityViewModel::class.java)) {
            return ScheduleActivityViewModel(localRepository) as T
        } else {
            throw IllegalArgumentException("Wrong viewModel class passed")
        }
    }

}
