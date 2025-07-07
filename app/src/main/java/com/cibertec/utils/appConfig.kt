package com.cibertec.utils

import android.app.Application
import android.content.Context
import com.cibertec.data.InitBD

class appConfig: Application() {
    companion object {
        lateinit var CONTEXT: Context
        lateinit var BD: InitBD
        var DB_NAME="cine.bd"
        var VERSION=1
    }

    override fun onCreate() {
        super.onCreate()
        CONTEXT = applicationContext
        BD = InitBD()
    }
}