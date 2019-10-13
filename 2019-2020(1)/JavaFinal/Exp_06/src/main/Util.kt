package main

import java.io.*
import java.nio.charset.StandardCharsets
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import java.sql.Blob
import java.sql.SQLException
import java.util.*
import kotlin.experimental.and

object Util {

    @Throws(SQLException::class, IOException::class)
    fun blobToString(blob: Blob): String {
        val reString: String
        val `is` = blob.binaryStream
        val byteArrayInputStream = `is` as ByteArrayInputStream
        val byteData = ByteArray(byteArrayInputStream.available()) //byteArrayInputStream.available()返回此输入流的字节数
        byteArrayInputStream.read(byteData, 0, byteData.size) //将输入流中的内容读到指定的数组
        reString = String(byteData, StandardCharsets.UTF_8) //再转为String，并使用指定的编码方式
        `is`.close()
        return reString
    }

    @JvmStatic
    fun getMD5(input: String): String {
        return try {
            //拿到一个MD5转换器（如果想要SHA1加密参数换成"SHA1"）
            val messageDigest = MessageDigest.getInstance("MD5")
            //输入的字符串转换成字节数组
            val inputByteArray = input.toByteArray()
            //inputByteArray是输入字符串转换得到的字节数组
            messageDigest.update(inputByteArray)
            //转换并返回结果，也是字节数组，包含16个元素
            val resultByteArray = messageDigest.digest()
            //字符数组转换成字符串返回
            byteArrayToHex(resultByteArray)

        } catch (e: NoSuchAlgorithmException) {
            "null"
        }

    }

    private fun byteArrayToHex(byteArray: ByteArray): String {
        //首先初始化一个字符数组，用来存放每个16进制字符
        val hexDigits = charArrayOf('0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F')
        //new一个字符数组，这个就是用来组成结果字符串的（解释一下：一个byte是八位二进制，也就是2位十六进制字符）
        val resultCharArray = CharArray(byteArray.size * 2)
        //遍历字节数组，通过位运算（位运算效率高），转换成字符放到字符数组中去
        var index = 0
        for (b in byteArray) {
            resultCharArray[index++] = hexDigits[b.toInt().ushr(4) and 0xf]
            resultCharArray[index++] = hexDigits[(b and 0xf).toInt()]
        }

        //字符数组组合成字符串返回
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
            val `is`: InputStream = FileInputStream(file)
            val reader: Reader = InputStreamReader(`is`)
            val sb = StringBuilder()
            var index: Int
            while (-1 != reader.read().also { index = it }) {
                sb.append(index.toChar())
            }
            `is`.close()
            reader.close()
            return sb.toString()
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return null
    }
}
