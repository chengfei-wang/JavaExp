package util

import java.io.File

class CONF(
        var server: String,
        var user: String,
        val password: String
) {
    enum class MODE {
        DEBUG, RELEASE
    }

    companion object {
        private val mode = MODE.DEBUG

        const val dateFormat = "yyyy/MM/dd-HH:mm:ss"

        val root: String
            get() {
                val url = CONF::class.java.classLoader.getResource("./")
                return File(File(url!!.path).parent).parent
            }
        val conf: CONF
            get() {
                val conf = when (mode) {
                    MODE.DEBUG -> {
                        File("$root/conf/config.debug")
                    }
                    MODE.RELEASE -> {
                        File("$root/conf/config.release")
                    }
                }
                val json = FileUtil.readJson(conf)
                val server = json.getString("server")
                val user = json.getString("user")
                val password = json.getString("password")
                return CONF(server, user, password)
            }

    }
}

