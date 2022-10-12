package test.interview.extraedgeassignment.ui.single_rocket

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.Job
import test.interview.extraedgeassignment.data.model.RocketsItem
import test.interview.extraedgeassignment.data.repository.CategoriesRepository
import test.interview.extraedgeassignment.utils.BackgroundThread

class SingleRocketViewModel(private val repository: CategoriesRepository) : ViewModel() {
    private lateinit var job: Job

    private val _rocketItem = MutableLiveData<RocketsItem>()
    val rocketItem: LiveData<RocketsItem>
        get() = _rocketItem

    fun getRocketItem(rocketId: String) {
        job = BackgroundThread.ioThenMain(
            { repository.getSingleRocket(rocketId) },
            {
                _rocketItem.value = it!!
            }
        )
    }
}