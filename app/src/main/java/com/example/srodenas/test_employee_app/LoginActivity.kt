package com.example.srodenas.test_employee_app

import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.example.srodenas.test_employee_app.network.ApiService
import com.example.srodenas.test_employee_app.network.RequestEmployee
import com.example.srodenas.test_employee_app.network.ResponseEmployee
import kotlinx.coroutines.launch
import retrofit2.Response

class LoginActivity : AppCompatActivity() {

    private lateinit var apiService : ApiService
    private var token:String? =null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        test()
    }

    private fun test() {
        apiService = ApiService() //creamos el servicio
        testRegister()
       // testLogin()
    }

    private fun testRegister() {
        val tUser  = RequestEmployee("1234567", "prueba", "santi de pruebas", "Para pruebas", "2000", "123456789", "https://cdn.pixabay.com/photo/2023/05/27/19/15/call-center-8022155_960_720.jpg", true, "token123") // Solo con dni y password

        var result : Result<ResponseEmployee>
        lifecycleScope.launch {
            result = apiService.registerService(tUser)
            result
                .onSuccess { Toast.makeText(this@LoginActivity, "Usuario registrado", Toast.LENGTH_LONG).show() }         //callBack (función de orden superior. Sólo si fue exitoso
                .onFailure { //función de orden superior. Sólo si no fue existoso.
                    Toast.makeText(this@LoginActivity, it.message, Toast.LENGTH_LONG).show()
                }

        }

    }

    private fun testLogin() {
        val tLogin = RequestEmployee(dni="1234567", password = "prueba")
        var result : Result<ResponseEmployee>
        lifecycleScope.launch {
            result = apiService.loginService(tLogin)
            result
                .onSuccess {
                    Toast.makeText(this@LoginActivity, "Usuario logueado con token: ${it.token}", Toast.LENGTH_LONG).show()
                    registerTokenShared(it.token)
                    token = it.token  //lo guardamos, para después utilizarlo en el activity.
                }         //callBack (función de orden superior. Sólo si fue exitoso
                .onFailure { //función de orden superior. Sólo si no fue existoso.
                    Toast.makeText(this@LoginActivity, it.message, Toast.LENGTH_LONG).show()
                }

        }


    }

    private fun registerTokenShared(token: String?) {
        val shared = getSharedPreferences("MyPrefsApp", MODE_PRIVATE)
        val edit = shared.edit()
        edit.putString ("token", token)
        edit.apply()
    }

    private fun loadTokenShared() : String? {
        val shared = getSharedPreferences("MyPrefsApp", MODE_PRIVATE)
        val token = shared.getString("token", null)
        return token
    }
}