package org.inu.iot.iotproject.model

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName

class SanitizerDetailModel {
    @SerializedName("code")
    @Expose
    var code: Int = 0

    @SerializedName("data")
    @Expose
    var data: Data = Data()

    @SerializedName("msg")
    @Expose
    var msg: String = ""

    @SerializedName("success")
    @Expose
    var success: Boolean = true
}

class Address {
    @SerializedName("city")
    @Expose
    var city: String = ""

    @SerializedName("code")
    @Expose
    var code: String = ""

    @SerializedName("position")
    @Expose
    var position: String = ""

    @SerializedName("street")
    @Expose
    var street: String = ""

}

class Data {
    @SerializedName("address")
    @Expose
    var address: Address = Address()

    @SerializedName("disinfectant")
    @Expose
    var disinfectant: Disinfectant = Disinfectant()

    @SerializedName("id")
    @Expose
    var id: Int = 0

    @SerializedName("runStatus")
    @Expose
    var runStatus: String = "RUN"

    @SerializedName("serialNumber")
    @Expose
    var serialNumber: String = ""

}

class Disinfectant {
    @SerializedName("currentCapacity")
    @Expose
    var currentCapacity: Int = 0

    @SerializedName("id")
    @Expose
    var id: Int = 0

    @SerializedName("percent")
    @Expose
    var percent: Int = 0

    @SerializedName("totalCapacity")
    @Expose
    var totalCapacity: Int = 0

}