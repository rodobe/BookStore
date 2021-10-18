package com.example.bookstore.networkUtils

import com.example.bookstore.networkUtils.ErrorCodes.GENERIC_ERROR
import com.google.gson.Gson
import retrofit2.Response

object NetworkResponseHandler {

    suspend fun <T : Any> apiCall(call: suspend () -> Response<T>): Result<T> {
        val response: Response<T>
        return try {
            response = call.invoke()
            if (!response.isSuccessful) {
                Result.Error(handleNetworkError(response.errorBody()?.string()))
            } else {
                response.body()?.let { Result.Success(it) } ?: Result.Error(handleNetworkError())
            }
        } catch (throwable: Throwable) {
            Result.Error(handleNetworkError())
        }
    }

    private fun handleNetworkError(errorBody: String? = null): NetworkError {
        return errorBody?.let {
            Gson().fromJson(errorBody, NetworkErrorResponse::class.java).error
        } ?: NetworkError(GENERIC_ERROR)
    }
}