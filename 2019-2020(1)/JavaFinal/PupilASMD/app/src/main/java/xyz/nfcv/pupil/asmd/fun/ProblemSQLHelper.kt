package xyz.nfcv.pupil.asmd.`fun`

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import xyz.nfcv.pupil.asmd.app.APP.Companion.db
import java.util.*
import kotlin.collections.ArrayList

class ProblemSQLHelper(context: Context): SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION) {

    class Exam(val examId: String, val title: String, val createTime: Date, val problems: ArrayList<ASMD.Problem>, val answers: ArrayList<Int>, val limitTime: Int)

    class Record(val name: String, val examId: String, val finishTime: Date, val answers: ArrayList<Int>, val totalTime: Int)

    fun addExam(exam: Exam) {
        val values = ContentValues()
        values.put("exam_id", exam.examId)
        values.put("title", exam.title)
        values.put("create_time", exam.createTime.time)
        values.put("problems", exam.problems.toJson())
        values.put("answers", exam.answers.toJson())
        values.put("limit_time", exam.limitTime)
        db.insert(TABLE_EXAM, null, values)
    }

    fun getExams(): ArrayList<Exam> {
        val list = ArrayList<Exam>()
        val cursor = db.rawQuery("select exam_id, title, create_time, problems, answers, limit_time from $TABLE_EXAM", arrayOf())
        if(cursor.count > 0 && cursor.moveToFirst()) {
            do {
                val examId = cursor.getString(0)
                val title = cursor.getString(1)
                val createTime = Date(cursor.getLong(2))
                val problems = cursor.getString(3).toProblems()
                val answers = cursor.getString(4).toAnswers()
                val limitTime = cursor.getInt(5)
                list.add(Exam(examId, title, createTime, problems, answers, limitTime))
            } while (cursor.moveToNext())
        }
        cursor.close()
        return list
    }

    fun addRecord(record: Record) {
        val values = ContentValues()
        values.put("name", record.name)
        values.put("exam_id", record.examId)
        values.put("finish_time", record.finishTime.time)
        values.put("answers", record.answers.toJson())
        values.put("total_time", record.totalTime)
        db.insert(TABLE_RECORD, null, values)
    }

    fun getRecords(): ArrayList<Record> {
        val list = ArrayList<Record>()
        val cursor = db.rawQuery("select name, exam_id, finish_time, answers, total_time from $TABLE_RECORD", arrayOf())
        if(cursor.count > 0 && cursor.moveToFirst()) {
            do {
                val name = cursor.getString(0)
                val examId = cursor.getString(1)
                val finishTime = Date(cursor.getLong(2))
                val answers = cursor.getString(3).toAnswers()
                val totalTime = cursor.getInt(4)
                list.add(Record(name, examId, finishTime, answers, totalTime))
            } while (cursor.moveToNext())
        }
        cursor.close()
        return list
    }

    fun getRecordByName(name: String): ArrayList<Record> {
        val list = ArrayList<Record>()
        val cursor = db.rawQuery("select name, exam_id, finish_time, answers, total_time from $TABLE_RECORD where name=?", arrayOf(name))
        if(cursor.count > 0 && cursor.moveToFirst()) {
            do {
                val examId = cursor.getString(1)
                val finishTime = Date(cursor.getLong(2))
                val answers = cursor.getString(3).toAnswers()
                val totalTime = cursor.getInt(4)
                list.add(Record(name, examId, finishTime, answers, totalTime))
            } while (cursor.moveToNext())
        }
        cursor.close()
        return list
    }

    fun getRecordByExamId(examId: String): ArrayList<Record> {
        val list = ArrayList<Record>()
        val cursor = db.rawQuery("select name, exam_id, finish_time, answers, total_time from $TABLE_RECORD where exam_id=?", arrayOf(examId))
        if(cursor.count > 0 && cursor.moveToFirst()) {
            do {
                val name = cursor.getString(0)
                val finishTime = Date(cursor.getLong(2))
                val answers = cursor.getString(3).toAnswers()
                val totalTime = cursor.getInt(4)
                list.add(Record(name, examId, finishTime, answers, totalTime))
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
        const val TABLE_RECORD = "record"
        const val TABLE_EXAM = "exam"
        const val CREATE_TABLE_RECORD = "create table $TABLE_RECORD(_id integer primary key autoincrement, name text not null, exam_id text not null, finish_time timestamp not null, answers text not null, total_time integer not null)"
        const val CREATE_TABLE_EXAM = "create table $TABLE_EXAM(_id integer primary key autoincrement, exam_id varchar(32) not null unique, title text not null, create_time timestamp not null, problems text not null, answers text not null, limit_time integer not null)"

    }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(CREATE_TABLE_EXAM)
        db?.execSQL(CREATE_TABLE_RECORD)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {}
}