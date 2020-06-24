package android.ocssample.remote

import android.ocssample.models.Contents
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface OcsApi {
    @GET("contents")
    suspend fun fetchContents(@Query("search",encoded = true) title: String): Response<Contents>

    companion object {
        private const val baseUrl = "https://api.ocs.fr/apps/v2/"
        operator fun invoke(): OcsApi {
            return Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(baseUrl)
                .build()
                .create(OcsApi::class.java)
        }
    }
}