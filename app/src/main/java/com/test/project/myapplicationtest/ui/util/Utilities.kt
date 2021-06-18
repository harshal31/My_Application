package com.test.project.myapplicationtest.ui.util

import android.content.Context
import android.net.ConnectivityManager
import android.util.Log
import androidx.core.content.getSystemService
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.test.project.myapplicationtest.base.base_view_model.BaseViewModel
import com.test.project.myapplicationtest.ui.main_activity.MainViewModel
import java.text.FieldPosition

/**
 * Created by Harshal Chaudhari on 16/06/21.
 */

fun Context.isConnected(): Boolean {
    val connectivityManager = getSystemService<ConnectivityManager>()
    val networkInfo = connectivityManager?.activeNetworkInfo
    return networkInfo?.isConnected ?: false
}


fun <T> mutableLiveData(default: T? = null) = MutableLiveData<T>(default)
fun <T> mutableLiveDataOf() = MutableLiveData<ServerResult<T>>()


inline fun <T> MutableLiveData<ServerResult<T>>.observeOnce(owner: LifecycleOwner, crossinline block: ServerResult<T>.() -> Unit) {
    observe(owner) {
        if (it != null) {
            it.block()
            value = null
        }
    }
}

inline fun <reified T> T.logger(msg: String) {
    Log.d(T::class.java.simpleName, msg)
}

inline fun MainViewModel.onPagination(currentPos: Int, crossinline paginate: (Int) -> Unit) {
    val mPos = currentPos + 1
    if (mPos > prevPosition) {
        prevPosition = mPos
        if ((mPos % 10) == 0) {
            serverStatus.postValue(ServerStatus.ON_PAGINATION)
            paginate(mPos)
        }
    }
}

