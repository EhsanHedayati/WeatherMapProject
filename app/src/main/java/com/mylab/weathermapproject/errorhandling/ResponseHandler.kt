package com.mylab.weathermapproject.errorhandling


import retrofit2.HttpException
import java.net.SocketTimeoutException

open class ResponseHandler {

    fun <T>handleSuccess(data: T): Resource<T> {
        return Resource.success(data)
    }

    fun <T>handleException(e: Exception): Resource<T> {
        return when(e) {
            is HttpException -> Resource.error(getErrorMessage(e.code()), null)
            is SocketTimeoutException -> Resource.error("timeOut", null)
            else -> Resource.error(getErrorMessage(Int.MAX_VALUE), null)
        }

    }

    private fun getErrorMessage(code: Int): String{
        return when (code){
            401 -> "Unauthorized"
            404 -> "Not found"
            else -> "Something went wrong"
        }

    }
}