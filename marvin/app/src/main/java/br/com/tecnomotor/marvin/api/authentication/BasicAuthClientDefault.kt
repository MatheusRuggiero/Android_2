package br.com.tecnomotor.marvin.api.authentication

import android.content.Context
import br.com.tecnomotor.marvin.utils.constant.ParameterSharedPreferences.LINK_OPTION_DEFAULT
import br.com.tecnomotor.marvin.utils.constant.ParameterSharedPreferences.SHARED_PREFERENCES_CONFIGURATION_SYNCHRONIZATION
import br.com.tecnomotor.marvin.utils.global.GlobalUtil
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class BasicAuthClientDefault<T>(userName: String, password: String, context: Context) {

    private val client = OkHttpClient.Builder()
        .addInterceptor(BasicAuthInterceptor(userName, password))
        .readTimeout(600000, TimeUnit.SECONDS)
        .connectTimeout(600000, TimeUnit.SECONDS)
        .build()

    private val urlServer = GlobalUtil.method.recoverSharedPreferences(context.getSharedPreferences(SHARED_PREFERENCES_CONFIGURATION_SYNCHRONIZATION, Context.MODE_PRIVATE), LINK_OPTION_DEFAULT)

    private val gson: Gson = GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS")
        .setLenient()
        .create();

    private val retrofit = Retrofit.Builder()
        .baseUrl(urlServer!!)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    fun create(service: Class<T>): T {
        return retrofit.create(service)
    }
}