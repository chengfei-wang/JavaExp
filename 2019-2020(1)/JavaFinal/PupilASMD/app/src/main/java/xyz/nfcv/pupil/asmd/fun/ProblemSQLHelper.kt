package xyz.nfcv.pupil.asmd.`fun`

import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import xyz.nfcv.pupil.asmd.app.APP
import xyz.nfcv.pupil.asmd.app.APP.Companion.db
import java.util.*
import kotlin.collections.ArrayList

class ProblemSQLHelper: SQLiteOpenHelper(APP.context, DB_NAME, null, DB_VERSION) {

    class Record(val time: Date, val problems: ArrayList<ASMD.Problem>, val answers: ArrayList<Int>, val totalTime: Int)

    fun addRecord(record: Record) {
        val values = ContentValues()
        values.put("time", record.time.time)
        values.put("problems", record.problems.toJson())
        values.put("answers", record.answers.toJson())
        values.put("total_time", record.totalTime)
        db.insert(TABLE_PROBLEM_HISTORY, null, values)
    }

    fun getRecords(): ArrayList<Record> {
        val list = ArrayList<Record>()
        val cursor = db.rawQuery("select time, problems, answers, total_time from $TABLE_PROBLEM_HISTORY", arrayOf())
        if(cursor.count > 0 && cursor.moveToFirst()) {
            do {
                val time = Date(cursor.getLong(0))
                val problems = cursor.getString(1).toProblems()
                val answers = cursor.getString(2).toAnswers()
                val totalTime = cursor.getInt(3)
                list.add(Record(time, problems, answers, totalTime))
            } while (cursor.moveToNext())
        }
        cursor.close()
        return list
    }

    private fun ArrayList<*>.toJson() = Gson().toJson(this)
    private fun String.toProblems(): ArrayList<ASMD.Problem> = Gson().fromJson(this, object : TypeToken<ArrayList<ASMD.Problem>>(){}.type)
    private fun String.toAnswers(): ArrayList<Int> = Gson().fromJson(this, object : TypeToken<ArrayList<Int>>(){}.type)

    companion object {
        const val DB_NAME = "asdm.sqlite3"
        const val DB_VERSION = 1
        const val TABLE_PROBLEM_HISTORY = "problem_history"
        const val CREATE_TABLE_PROBLEM_HISTORY = "create table $TABLE_PROBLEM_HISTORY(_id integer primary key autoincrement, time timestamp not null, problems text not null, answers text not null, total_time integer not null)"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(CREATE_TABLE_PROBLEM_HISTORY)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {}
}