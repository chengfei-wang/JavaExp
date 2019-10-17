package model

import com.alibaba.fastjson.JSONObject

class Message<T>(val code: Int, val msg: String, val data: T? = null) {
    override fun toString(): String {
        return JSONObject.toJSONString(this)
    }
}