package test.interview.extraedgeassignment.ui.flicker_list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.Job
import test.interview.extraedgeassignment.data.model.Rockets
import test.interview.extraedgeassignment.data.repository.CategoriesRepository
import test.interview.extraedgeassignment.utils.BackgroundThread

class RocketsViewModel(private val repository: CategoriesRepository) : ViewModel() {

    private lateinit var job: Job

    private val _entryModels = MutableLiveData<Rockets>()
    val entryModels: LiveData<Rockets>
        get() = _entryModels

    fun getEntryModels() {
        job = BackgroundThread.ioThenMain(
            { repository.getCategories() },
            {
                _entryModels.value = it
            }
        )
    }

    override fun onCleared() {
        super.onCleared()

        if (::job.isInitialized) job.cancel()

    }

}