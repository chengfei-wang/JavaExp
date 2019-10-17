package config

import util.CONF
import java.sql.Connection
import java.sql.DriverManager
import java.sql.SQLException

fun main() {
    Config.init()
}

/**
 * create database ichat;
 * alter database ichat character set utf8mb4;
 * use ichat;
 */

object Config {
    private const val CREATE_TABLE_USER = "create table user(_id integer primary key auto_increment, uid varchar(32) not null unique, nickname varchar(32) not null unique, token text not null, password text not null)"

    private val SERVER = CONF.conf.server
    private val MySQLConnStr =
            "jdbc:mysql://$SERVER/ichat?useUnicode=true&characterEncoding=utf8&useSSL=false"
    private val USER = CONF.conf.user
    private val PASSWORD = CONF.conf.password

    private var conn: Connection? = null
        get() {
            if (field == null || (field != null && field!!.isClosed)) {
                try {
                    Class.forName("com.mysql.jdbc.Driver")
                    field = DriverManager.getConnection(MySQLConnStr, USER, PASSWORD)
                } catch (e: ClassNotFoundException) {
                    e.printStackTrace()
                } catch (e: SQLException) {
                    e.printStackTrace()
                }

            }

            return field
        }
    fun init() {
        val st0 = conn!!.createStatement()
        st0.execute(CREATE_TABLE_USER)
        st0.close()
    }
}

fun c(): A {
    return object : A {
        override fun a() {

        }

        override fun b() {

        }

    }
}

interface A {
    fun a()
    fun b()
}

