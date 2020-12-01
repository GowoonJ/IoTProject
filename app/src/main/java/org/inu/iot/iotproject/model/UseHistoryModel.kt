package org.inu.iot.iotproject.model

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName

class UseHistoryModel {

    @SerializedName("code")
    @Expose
    var code: Int = 0

    @SerializedName("data")
    @Expose
    var data: ArrayList<Datum> = ArrayList()

    @SerializedName("msg")
    @Expose
    private var msg: String = ""

    @SerializedName("success")
    @Expose
    private var success: Boolean = false
}
class Datum {
    @SerializedName("sumOfUseAmount")
    @Expose
    var sumOfUseAmount: Int = 0

    @SerializedName("UseCount")
    @Expose
    var useCount: Int = 0

    @SerializedName("date")
    @Expose
    var date: String = ""
}