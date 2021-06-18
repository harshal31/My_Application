package com.test.project.myapplicationtest.ui.util

import com.test.project.myapplicationtest.base.base_view_model.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

/**
 * Created by Harshal Chaudhari on 16/06/21.
 */


suspend fun <T> BaseViewModel.retroCall(request: suspend () -> Response<T>, response: (ServerResult<T>) -> Unit) {
    serverStatus.postValue(ServerStatus.ON_PROGRESS)
    withContext(Dispatchers.IO) {
        try {
            val res = request.invoke()
            if (res.isSuccessful && res.code() == 200 && res.body() != null) {
                serverStatus.postValue(ServerStatus.ON_SUCCESS)
                response(ServerResult.Success(res.body()))
            } else {
                serverStatus.postValue(ServerStatus.ON_FAILURE)
                response(ServerResult.Failure("Server Error Occur"))
            }
        } catch (t: Throwable) {
            serverStatus.postValue(ServerStatus.ON_FAILURE)
            response(ServerResult.Failure("Server Error Occur"))
        }
    }
}

suspend fun onMainDispatcher(block: () -> Unit) {
    withContext(Dispatchers.Main) {
        block()
    }
}

sealed interface ServerResult<T> {
    data class Success<T>(val response: T?) : ServerResult<T>
    data class Failure<T>(val message: String) : ServerResult<T>
}

inline fun <T> ServerResult<T>.onSuccess(crossinline block: (T) -> Unit) {
    if (this is ServerResult.Success) {
        block(this.response!!)
    }
}

inline fun <T> ServerResult<T>.onFailure(crossinline block: (String) -> Unit) {
    if (this is ServerResult.Failure) {
        block(this.message)
    }
}