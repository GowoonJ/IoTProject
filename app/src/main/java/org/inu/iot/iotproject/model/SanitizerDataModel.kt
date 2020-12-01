package org.inu.iot.iotproject.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class SanitizerDataModel {

    @SerializedName("capacity")
    @Expose
    var capaticy = 0

    @SerializedName("id")
    @Expose
    var id = 0

    @SerializedName("runStatus")
    @Expose
    var runStatus = "RUN"
}