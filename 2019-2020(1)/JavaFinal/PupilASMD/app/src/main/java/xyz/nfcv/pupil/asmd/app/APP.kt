package xyz.nfcv.pupil.asmd.app

import android.app.Application
import android.content.Context

class APP : Application() {
    override fun onCreate() {
        super.onCreate()
        context = applicationContext
    }

    companion object {
        lateinit var context: Context
    }
}