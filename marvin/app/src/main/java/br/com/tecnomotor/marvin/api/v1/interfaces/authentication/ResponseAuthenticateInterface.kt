package br.com.tecnomotor.marvin.api.v1.interfaces.authentication

import br.com.tecnomotor.marvin.api.v1.model.global.ResponseAuthenticate
import br.com.tecnomotor.marvin.utils.constant.URL.AUTHENTICATION_MOBILE
import br.com.tecnomotor.marvin.utils.constant.URL.CRMANAGER
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.QueryMap

interface ResponseAuthenticateInterface {
    @Headers("Content-Type:application/octet-stream")
    @GET("$CRMANAGER$AUTHENTICATION_MOBILE")
    fun getResponseAuthenticate(@QueryMap params: Map<String, String>): Call<ResponseAuthenticate>
}