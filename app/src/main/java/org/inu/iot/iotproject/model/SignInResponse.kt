package org.inu.iot.iotproject.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class SignInResponse {
    @SerializedName("success")
    @Expose
    var success : Boolean = true

    @SerializedName("code")
    @Expose
    var code : Int = 0

    @SerializedName("msg")
    @Expose
    var msg : String = ""

    @SerializedName("data")
    @Expose
    var data : String = ""
}