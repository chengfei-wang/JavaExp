package xyz.nfcv.pupil.asmd.app

import android.app.Application
import android.content.Context
import xyz.nfcv.pupil.asmd.`fun`.ProblemSQLHelper

class APP : Application() {
    override fun onCreate() {
        super.onCreate()
        context = applicationContext
    }

    companion object {
        lateinit var context: Context
        val db = ProblemSQLHelper().writableDatabase
    }
}