package com.example.srodenas.test_employee_app.network

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ResponseEmployee(
    @SerializedName("name")
    @Expose
    var name : String?,

    @SerializedName("dni")
    @Expose
    val dni : String?,

    @SerializedName("password")
    @Expose
    var password : String?,

    @SerializedName("description")
    @Expose
    var description: String?,

    @SerializedName("salary")
    @Expose
    var salary: String?,

    @SerializedName("phone")
    @Expose
    var phone: String?,

    @SerializedName("urlImage")
    @Expose
    var urlImage: String ? = null,

    @SerializedName("disponible")
    @Expose
    var disponible: Boolean = true,

    @SerializedName("token")
    @Expose
    var token:String ? = null,

    @SerializedName("msg")
    @Expose
    var msg: String ?= null  //para la respuesta de la api personalizada.
)
