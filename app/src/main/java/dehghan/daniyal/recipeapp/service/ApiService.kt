package dehghan.daniyal.recipeapp.service

import dehghan.daniyal.recipeapp.model.CategoriesResponse
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET


private val retrofit = Retrofit
    .Builder()
    .baseUrl("www.themealdb.com/api/json/v1/1/")
    .addConverterFactory(GsonConverterFactory.create())
    .build()

val recipeService = retrofit.create(ApiService::class.java)

interface ApiService {

    @GET("categories.php")
    suspend fun getCategories(): CategoriesResponse
}