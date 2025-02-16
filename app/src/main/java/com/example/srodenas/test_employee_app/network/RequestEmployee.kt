package com.example.srodenas.test_employee_app.network

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class RequestEmployee(
     @SerializedName("dni")
     @Expose
     var dni : String,

     @SerializedName("password")
     @Expose
     var password : String,

     @SerializedName("name")
     @Expose
     var name : String? = null,

     @SerializedName("description")
     @Expose
     var description : String? = null,

     //@SerializedName("salary")
   //  @Expose
     var salary : String? = null,

     @SerializedName("phone")
     @Expose
     var phone : String? = null,

     @SerializedName("urlImage")
     @Expose
     var urlImage : String? = null,

     @SerializedName("disponible")
     @Expose
     var disponible : Boolean = true,

     @SerializedName("token")
     @Expose
     var token: String? = null

){
    constructor (dni : String, password: String):
            this(dni = dni,
                password = password,
                null,
                null,
                null,
                null,
                null,
                true,
                null)
}
