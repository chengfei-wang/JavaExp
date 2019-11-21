package xyz.nfcv.pupil.asmd.`fun`

import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import xyz.nfcv.pupil.asmd.app.APP

class ProblemSQLHelper: SQLiteOpenHelper(APP.context, DB_NAME, null, DB_VERSION) {
    companion object {
        const val DB_NAME = "asdm.sqlite3"
        const val DB_VERSION = 1
    }

    override fun onCreate(db: SQLiteDatabase?) {
        
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {}
}