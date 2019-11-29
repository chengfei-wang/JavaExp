package xyz.nfcv.pupil.asmd.app

import android.app.Application
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import xyz.nfcv.pupil.asmd.`fun`.ProblemSQLHelper

class APP : Application() {
    override fun onCreate() {
        super.onCreate()
        context = applicationContext
        helper = ProblemSQLHelper(context)
        db = helper.writableDatabase
    }

    companion object {
        lateinit var context: Context
        lateinit var db: SQLiteDatabase
        lateinit var helper: ProblemSQLHelper
    }
}