package com.akondi.cleancoremvvm.core.platform

import android.content.Context
import com.akondi.cleancoremvvm.core.extension.connectivity
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Injectable class which returns information about the network connection state.
 */
@Singleton
class NetworkHandler
@Inject constructor(private val context: Context) {
    val isConnected get() = context.connectivity
}