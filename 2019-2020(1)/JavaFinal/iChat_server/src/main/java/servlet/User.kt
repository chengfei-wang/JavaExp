package servlet

import model.Message
import util.DB
import util.Value
import util.Value.fields
import java.io.PrintWriter
import java.util.HashMap
import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@WebServlet(name = "user", urlPatterns = ["/api/user/*"])
class User: HttpServlet() {

    class Model(val uid: String, val nickname: String, val ip: String, val token: String, val password: String) {
        fun checkPassword(uid: String, password: String) {
            val conn = DB.connection
            val ps = conn.prepareStatement("select * from user")
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
                val uid = fields["uid"]
                val password = fields["password"]

            }

            "register" -> {

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