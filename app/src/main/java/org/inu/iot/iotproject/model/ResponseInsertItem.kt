package org.inu.iot.iotproject.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ResponseInsertItem {

    @SerializedName("code")
    @Expose
    var code : Int = 0

    @SerializedName("msg")
    @Expose
    var msg : String = ""

    @SerializedName("success")
    @Expose
    var success : Boolean = true
}