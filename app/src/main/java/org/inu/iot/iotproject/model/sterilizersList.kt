package org.inu.iot.iotproject.model

import com.google.gson.annotations.SerializedName

class sterilizersList {

    @SerializedName("code")
    var code : Int = 0

    @SerializedName("data")
    var data : ArrayList<SanitizerDataModel> = ArrayList()

    @SerializedName("msg")
    var msg = ""

    @SerializedName("success")
    var success = true
}