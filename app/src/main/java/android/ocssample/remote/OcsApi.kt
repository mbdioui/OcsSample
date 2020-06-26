package android.ocssample.remote

import android.ocssample.models.Contents
import android.ocssample.models.DetailLink
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url
import java.util.*

interface OcsApi {
    @GET("apps/v2/contents")
    suspend fun fetchContents(@Query("search", encoded = true) title: String): Response<Contents>

    @GET
    suspend fun fetchDetailLink(@Url fullUrl: String): Response<DetailLink>


    companion object {
        private const val baseUrl = "https://api.ocs.fr/"
        operator fun invoke(): OcsApi {
            return Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(baseUrl)
                .build()
                .create(OcsApi::class.java)
        }
    }
}