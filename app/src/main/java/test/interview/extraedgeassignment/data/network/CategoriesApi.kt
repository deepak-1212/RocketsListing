package test.interview.extraedgeassignment.data.network

import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import test.interview.extraedgeassignment.data.model.Rockets
import test.interview.extraedgeassignment.data.model.RocketsItem

interface CategoriesApi {

    @GET("rockets")
    suspend fun getCategories(): Response<Rockets>

    @GET("rockets/{rocket_id}")
    suspend fun getSingleRocket(
        @Path(
            value = "rocket_id",
            encoded = true
        ) singleRocketUrl: String
    ): Response<RocketsItem>

    companion object {
        operator fun invoke(): CategoriesApi {
            return Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://api.spacexdata.com/v4/")
                .build()
                .create(CategoriesApi::class.java)

        }
    }
}