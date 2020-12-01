package org.inu.iot.iotproject.model

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName

class changeHistory {
    @SerializedName("code")
    @Expose
    private var code: Int = 0

    @SerializedName("data")
    @Expose
    var data: ArrayList<Datas> = ArrayList()

    @SerializedName("msg")
    @Expose
    private var msg: String = ""

    @SerializedName("success")
    @Expose
    private var success: Boolean = true

    fun getCode(): Int? {
        return code
    }

    fun setCode(code: Int) {
        this.code = code
    }

    fun getMsg(): String? {
        return msg
    }

    fun setMsg(msg: String) {
        this.msg = msg
    }

    fun getSuccess(): Boolean? {
        return success
    }

    fun setSuccess(success: Boolean) {
        this.success = success
    }
}
class Datas {
    @SerializedName("changeHistoryId")
    @Expose
    var changeHistoryId: Int = 0

    @SerializedName("changeTime")
    @Expose
    var changeTime: String = ""
}