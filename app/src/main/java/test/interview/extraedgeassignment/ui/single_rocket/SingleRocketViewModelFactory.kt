package test.interview.extraedgeassignment.ui.single_rocket

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import test.interview.extraedgeassignment.data.repository.CategoriesRepository

@Suppress("UNCHECKED_CAST")
class SingleRocketViewModelFactory(private val repository: CategoriesRepository): ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return SingleRocketViewModel(repository) as T
    }

}