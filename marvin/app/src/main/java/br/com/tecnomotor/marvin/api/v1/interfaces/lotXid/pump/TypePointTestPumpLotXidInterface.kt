package br.com.tecnomotor.marvin.api.v1.interfaces.lotXid.pump

import br.com.tecnomotor.marvin.api.v1.model.pump.TypePointTestPumpSynchronize
import br.com.tecnomotor.marvin.utils.constant.URL.ANDROID
import br.com.tecnomotor.marvin.utils.constant.URL.API
import br.com.tecnomotor.marvin.utils.constant.URL.CRMANAGER
import br.com.tecnomotor.marvin.utils.constant.URL.LOT_XID
import br.com.tecnomotor.marvin.utils.constant.URL.SEPARATOR
import br.com.tecnomotor.marvin.utils.constant.URL.TYPE_POINT_TEST_PUMP
import br.com.tecnomotor.marvin.utils.constant.URL.V_1
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.QueryMap

interface TypePointTestPumpLotXidInterface {
    @Headers("Content-Type:application/octet-stream")
    @GET(CRMANAGER + SEPARATOR + API + SEPARATOR + ANDROID + SEPARATOR + V_1 + SEPARATOR + TYPE_POINT_TEST_PUMP + SEPARATOR + LOT_XID)
    fun getLotXid(@QueryMap params: Map<String, String>): Call<List<TypePointTestPumpSynchronize>>
}