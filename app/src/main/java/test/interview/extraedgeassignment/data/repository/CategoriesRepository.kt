package test.interview.extraedgeassignment.data.repository

import test.interview.extraedgeassignment.data.network.CategoriesApi
import test.interview.extraedgeassignment.data.model.network.SafeApiRequest

class CategoriesRepository(private val categoriesApi: CategoriesApi): SafeApiRequest() {
    suspend fun getCategories() = apiRequest { categoriesApi.getCategories() }
    suspend fun getSingleRocket(rocketId: String) = apiRequest { categoriesApi.getSingleRocket(rocketId) }
}