package test.interview.extraedgeassignment.ui.flicker_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import test.interview.extraedgeassignment.data.repository.CategoriesRepository

@Suppress("UNCHECKED_CAST")
class RocketsViewModelFactory(private val repository: CategoriesRepository): ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return RocketsViewModel(repository) as T
    }

}