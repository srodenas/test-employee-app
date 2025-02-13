package com.example.srodenas.test_employee_app.network

 class ResponseEmployee(
    var name : String?,
    val dni : String?,
    var password : String?,
    var description: String?,
    var salary: String?,
    var phone: String?,
    var urlImage: String ? = null,
    var disponible: Boolean = true,
    var token:String ? = null,

    var msg: String ?= null  //para la respuesta.
)
