package com.afoxplus.yalistoadmin.ui.main

import com.afoxplus.network.global.AppProperties
import com.afoxplus.yalistoadmin.data.repository.global.GlobalRepository
import javax.inject.Inject

class AppPropertiesMain @Inject constructor(
    private val globalRepository: GlobalRepository
) : AppProperties {
    override fun getCurrencyID(): String = globalRepository.getCurrencyID()

    override fun getDeviceData(): String {
        var deviceData = ""
        try {
            val manufacturer = android.os.Build.MANUFACTURER
            val model = android.os.Build.MODEL
            val myVersion = android.os.Build.VERSION.RELEASE
            val osVersion = android.os.Build.VERSION.SDK

            deviceData = String.format(
                "%s|%s|%s|%s",
                manufacturer,
                model,
                myVersion,
                osVersion
            )
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
        return deviceData
    }

    override fun getUserUUID(): String = globalRepository.getUserUUID()

    override fun isAppDebug(): Boolean {
        return false
    }
}
