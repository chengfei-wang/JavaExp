package main

import java.io.*
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import java.util.*
import kotlin.experimental.and

object Util {

    @JvmStatic
    fun getMD5(input: String): String {
        return try {
            val messageDigest = MessageDigest.getInstance("MD5")
            val inputByteArray = input.toByteArray()
            messageDigest.update(inputByteArray)
            val resultByteArray = messageDigest.digest()
            byteArrayToHex(resultByteArray)
        } catch (e: NoSuchAlgorithmException) {
            "null"
        }

    }

    private fun byteArrayToHex(byteArray: ByteArray): String {
        val hexDigits = charArrayOf('0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F')
        val resultCharArray = CharArray(byteArray.size * 2)
        var index = 0
        for (b in byteArray) {
            resultCharArray[index++] = hexDigits[b.toInt().ushr(4) and 0xf]
            resultCharArray[index++] = hexDigits[(b and 0xf).toInt()]
        }
        return String(resultCharArray)
    }

    enum class Field { USERID, TOKEN, NICKNAME }

    @JvmStatic
    fun getFields(): HashMap<Field, String>? {
        try {
            val conf = readFile(CONF.CONF_FILE) ?: return null
            val map = HashMap<Field, String>()
            val fields = conf.split("::".toRegex()).toTypedArray()
            for (s in fields) {
                val entry = s.split("/".toRegex()).toTypedArray()
                when (entry[0]) {
                    "USERID" -> map[Field.USERID] = entry[1]
                    "TOKEN" -> map[Field.TOKEN] = entry[1]
                    "NICKNAME" -> map[Field.NICKNAME] = entry[1]
                }
            }
            if (map.containsKey(Field.USERID) && map.containsKey(Field.TOKEN) && map.containsKey(Field.NICKNAME)) {
                return map
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return null
    }

    @JvmStatic
    fun setFields(map: HashMap<Field?, String?>) {
        val userId = map[Field.USERID]!!.replace("/", "").replace(":", "")
        val token = map[Field.TOKEN]!!.replace("/", "").replace(":", "")
        val nickname = map[Field.NICKNAME]!!.replace("/", "").replace(":", "")
        val conf = "USERID/$userId::TOKEN/$token::NICKNAME/$nickname"
        writeFile(CONF.CONF_FILE, conf)
    }

    private fun writeFile(filename: String, content: String) {

        try {
            val file = File(filename)
            if (!file.parentFile.exists()) {
                file.parentFile.mkdirs()
            }
            if (!file.exists()) {
                file.createNewFile()
            }
            val fw = FileWriter(file)
            fw.write(content)
            fw.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun readFile(filename: String): String? {
        try {
            val file = File(filename)
            if (!file.exists()) {
                return null
            }
            val inputStream: InputStream = FileInputStream(file)
            val reader: Reader = InputStreamReader(inputStream)
            val builder = StringBuilder()
            var index: Int
            while (-1 != reader.read().also { index = it }) {
                builder.append(index.toChar())
            }
            inputStream.close()
            reader.close()
            return builder.toString()
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return null
    }

}
