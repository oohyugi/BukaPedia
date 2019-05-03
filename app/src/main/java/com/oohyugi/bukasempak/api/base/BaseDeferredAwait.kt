package com.oohyugi.bukasempak.api.base

import kotlinx.coroutines.*
import retrofit2.HttpException
import retrofit2.Response
import kotlin.coroutines.resume
/**
 * Created by oohyugi on 2019-05-01.
 * github: https://github.com/oohyugi
 */
suspend fun <T : Any> Deferred<Response<T>>.awaitResult(): BaseResult<T> {
    return suspendCancellableCoroutine { continuation ->

        GlobalScope.launch {
            try {
                val response = await()
                continuation.resume(
                    if (response.isSuccessful) {
                        val body = response.body()
                        body?.let {
                            BaseResult.Ok(it, response.raw())
                        } ?: "error".let {
                            if (response.code() ==200){
                                BaseResult.Exception(Exception("body is empty"))
                            }
                            else{
                                BaseResult.Exception(NullPointerException("Response body is null"))
                            }


                        }

                    } else {
                        BaseResult.Error(HttpException(response), response.raw())
                    }
                )
            }
            catch (e:Throwable){
                //  Log.e("DeferredAwait",e.message)
                continuation.resume(BaseResult.Exception(e))
            }

        }

        registerOnCompletion(continuation)
    }
}



private fun Deferred<Response<*>>.registerOnCompletion(continuation: CancellableContinuation<*>) {
    continuation.invokeOnCancellation {
        if (continuation.isCancelled)
            try {
                cancel()
            } catch (ex: Throwable) {
                //Ignore cancel exception
                ex.printStackTrace()
            }
    }
}