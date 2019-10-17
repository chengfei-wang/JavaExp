package servlet

import model.Message
import util.DB
import util.Value
import util.Value.fields
import java.io.PrintWriter
import java.util.*
import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@WebServlet(name = "user", urlPatterns = ["/api/user/*"])
class User: HttpServlet() {

    class Model(val uid: String, val nickname: String, val ip: String, val token: String) {

        companion object {
            private val conn = DB.connection
            fun checkPassword(uid: String, password: String): Boolean {
                var isExist = false
                val ps = conn.prepareStatement("select uid, password from user where uid=? and password=? limit 1")
                ps.setString(1, uid)
                ps.setString(2, password)
                val rs = ps.executeQuery()
                if (rs.next()) isExist = true
                rs.close()
                ps.close()
                return isExist
            }

            fun updateToken(uid: String, ip: String): String {
                val token = "$uid::$ip::${Value.random()}::${Date().time}"
                val ps = conn.prepareStatement("update user set token=? where uid=?")
                ps.setString(1, token)
                ps.setString(2, uid)
                ps.execute()
                ps.close()
                return token
            }

            fun getNickname(uid: String): String {
                val nickname: String
                val ps = conn.prepareStatement("select nickname from user where uid=? limit 1")
                ps.setString(1, uid)
                val rs = ps.executeQuery()
                nickname = if (rs.next()) {
                    rs.getString("nickname")
                } else {
                    Value.DEFAULT_NICKNAME
                }
                rs.close()
                ps.close()
                return nickname
            }

            fun checkNickname(nickname: String): Boolean {
                val isExist: Boolean
                val ps = conn.prepareStatement("select nickname from user where nickname=? limit 1")
                ps.setString(1, nickname)
                val rs = ps.executeQuery()
                isExist = rs.next()
                rs.close()
                ps.close()

                return isExist
            }
        }
    }

    private var ip: String = "0.0.0.0"
    private lateinit var out: PrintWriter

    private fun <T> Message<T>.write() { out.write(this.toString()) }

    override fun doGet(req: HttpServletRequest?, resp: HttpServletResponse?) {
        doPost(req, resp)
    }

    override fun doPost(req: HttpServletRequest?, resp: HttpServletResponse?) {
        resp!!.characterEncoding = "UTF-8"
        req!!.characterEncoding = "UTF-8"
        out = resp.writer
        ip = Value.getIP(req)
        val route = try {
            req.requestURI.substring("/ichat/api/user/".length)
        } catch (e: StringIndexOutOfBoundsException) {
            Message<String>(-1, "route异常").write()
            return
        }

        val fields =  req.fields()

        when (route) {
            "login" -> {
                val uid = fields["uid"]!!
                val password = fields["password"]!!
                if (Model.checkPassword(uid, Value.getMD5(password))) {
                    val token = Value.getMD5(Model.updateToken(uid, ip))
                    val nickname = Model.getNickname(uid)
                    Message(0, "login success/登录成功", Model(uid, nickname, ip, token)).write()
                } else {
                    Message<String>(-1, "login fail/uid|pass e")
                }
                return
            }

            "register" -> {
                val nickname = fields["nickname"]!!
                val password = fields["password"]!!
                val confirm = fields["confirm"]!!
                if (password != confirm) {
                    Message<String>(-1, "密码不匹配")
                    return
                }
                if (Model.checkNickname(nickname)) {
                    Message<String>(-1, "昵称'$nickname'已被注册")
                    return
                }
            }

            "activate" -> {

            }

            "inactivate" -> {

            }

            else -> Message<String>(-999, "#TODO").write()
        }

        out.println(route)
    }
}