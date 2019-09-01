package com.akondi.cleancoremvvm.core.extension

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.widget.Toast

enum class ConnectivityMode {
    NONE,
    WIFI,
    MOBILE,
    OTHER,
    MAYBE
}

//var connectivityMode = ConnectivityMode.NONE

inline val Context.connectivity: ConnectivityMode get() {
    val connectivityManager = this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    connectivityManager.run {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getNetworkCapabilities(activeNetwork)?.run {
                return when {
                    hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> ConnectivityMode.WIFI
                    hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> ConnectivityMode.MOBILE
                    hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> ConnectivityMode.OTHER
                    hasTransport(NetworkCapabilities.TRANSPORT_BLUETOOTH) -> ConnectivityMode.MAYBE
                    else -> ConnectivityMode.NONE
                }
            }
        } else {
            @Suppress("DEPRECATION")
            activeNetworkInfo?.run {
                return when (type) {
                    ConnectivityManager.TYPE_WIFI -> ConnectivityMode.WIFI
                    ConnectivityManager.TYPE_MOBILE -> ConnectivityMode.MOBILE
                    ConnectivityManager.TYPE_ETHERNET -> ConnectivityMode.OTHER
                    ConnectivityManager.TYPE_BLUETOOTH -> ConnectivityMode.MAYBE
                    else -> ConnectivityMode.NONE
                }
            }
        }
    }
    return ConnectivityMode.NONE
}

inline fun <reified T : Activity> Context.startActivity() {
    startActivity(Intent(this, T::class.java))
}

fun Context.toast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}