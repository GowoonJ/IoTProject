package org.inu.iot.iotproject.model

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName




class UserRequest {
    @SerializedName("email")
    @Expose
    private var email: String = ""

    @SerializedName("password")
    @Expose
    private var password: String = ""

    constructor(email:String, password : String){
        this.email = email
        this.password = password
    }
}