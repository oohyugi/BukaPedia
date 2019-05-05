package com.oohyugi.bukasempak.api.base

import android.util.Log
import com.oohyugi.bukasempak.api.ApiClient
import retrofit2.Response
import java.io.IOException

/**
 * Created by oohyugi on 2019-05-04.
 * github: https://github.com/oohyugi
 */
open class BaseRepository {
    var apiClient = ApiClient.makeService("http://yogiputra.com/bl_api/")
    var apiClientBl = ApiClient.makeService("https://api.bukalapak.com/")
//    suspend fun <T: Any> safeApiCall(call: suspend ()-> Response<T>, errorMessage:String):T?{
//        val result: MyResult<T> = safeApiResult(call,errorMessage)
//        var data: T? = null
//
//        when(result){
//            is MyResult.Success->
//                data = result.data
//            is MyResult.Error->
//                Log.d("1.DataRepository", "$errorMessage & Exception - ${result.exception}")
//        }
//
//        return data
//    }
//
//    private suspend fun <T: Any> safeApiResult(call: suspend () -> Response<T>, errorMessage: String): MyResult<T> {
//
//        val response = call.invoke()
//        if(response.isSuccessful) return MyResult.Success(response.body()!!)
//
//        return MyResult.Error(IOException("Error Occurred during getting safe Api result, Custom ERROR - $errorMessage"))
//
//    }
suspend fun <T : Any> safeApiCall(call: suspend () -> MyResult<T>, errorMessage: String): MyResult<T> = try {
    call.invoke()
} catch (e: Exception) {
    MyResult.Error(IOException(errorMessage, e))
}
}