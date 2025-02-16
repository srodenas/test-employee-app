package com.example.srodenas.test_employee_app

import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
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
       // deleteTokenShared()
        apiService = ApiService() //creamos el servicio
       // testRegister()  //Para ver si funciona el registro
        if (loadTokenShared() == null)
            testLogin()         //Para ver si funciona el login
        else
            testLoginForAllEmployee()
        Toast.makeText(this, "fin", Toast.LENGTH_LONG).show()
    }

    private fun testRegister() {
        val tUser  = RequestEmployee("11234569", "prueba", "santi de pruebas2", "Para pruebas", "HIGH", null, "https://cdn.pixabay.com/photo/2023/05/27/19/15/call-center-8022155_960_720.jpg", true, null) // Solo con dni y password

        var result : Result<ResponseEmployee>
        lifecycleScope.launch {
            result = apiService.registerService(tUser)
            result
                .onSuccess {
                    Log.i("DEBUG_APP", "Se ha registrado correctamente, el usuario ${it.name} con dni ${it.dni}")
                    Toast.makeText(this@LoginActivity, "Se ha registrado correctamente, el usuario ${it.name} con dni ${it.dni}", Toast.LENGTH_LONG).show()
                }         //callBack (función de orden superior. Sólo si fue exitoso
                .onFailure { //función de orden superior. Sólo si no fue existoso.
                    Log.e("DEBUG_APP", "Error en la llamada al servicio, del usuario ${tUser}")
                    Toast.makeText(this@LoginActivity, it.message, Toast.LENGTH_LONG).show()
                }

        }

    }

    private fun testLogin() {
        val tLogin = RequestEmployee(dni="11234569", password = "prueba")
        var result : Result<ResponseEmployee>
        lifecycleScope.launch {
            result = apiService.loginService(tLogin)
            result
                .onSuccess {
                    Log.i("DEBUG_APP", "Se ha logueado correctamente, el usuario ${it.name} con dni ${it.dni} con token ${it.token}")
                    Toast.makeText(this@LoginActivity, "Se ha logueado correctamente, el usuario ${it.name} con dni ${it.dni} con token ${it.token}", Toast.LENGTH_LONG).show()
                    registerTokenShared(it.token)
                    token = it.token  //lo guardamos, para después utilizarlo en el activity, para más pruebas
                }         //callBack (función de orden superior. Sólo si fue exitoso
                .onFailure { //función de orden superior. Sólo si no fue existoso.
                    Log.e("DEBUG_APP", "Error en la llamada al servicio, del usuario ${tLogin}")
                    Toast.makeText(this@LoginActivity, it.message, Toast.LENGTH_LONG).show()
                }

        }


    }

    private fun testLoginForAllEmployee(){
        var result : Result<List<ResponseEmployee>>
        lifecycleScope.launch{
            result = apiService.getAllEmployee(token!!)
            result
                .onSuccess {
                    it.forEach {
                        Log.i("DEBUG_APP", "Employee: ${it}")
                    }
                }
                .onFailure {
                    Log.e("DEBUG_APP", "Error en la llamada al servicio, devolver todos los usuarios")
                    Toast.makeText(this@LoginActivity, it.message, Toast.LENGTH_LONG).show()
                }
        }
    }

    private fun registerTokenShared(token: String?) {
        val shared = getSharedPreferences("MyPrefsApp", MODE_PRIVATE)
        val edit = shared.edit()
        edit.putString ("token", token)
        edit.apply()
        val savedToken = shared.getString("token", null)
    }

    private fun loadTokenShared() : String? {
        val shared = getSharedPreferences("MyPrefsApp", MODE_PRIVATE)
        token = shared.getString("token", null)
        return token
    }

    private fun deleteTokenShared(){
        val shared = getSharedPreferences("MyPrefsApp", MODE_PRIVATE)
        if (shared.contains("token")){
            val edit = shared.edit()
            edit.remove("token").apply()
            Log.d("DEBUG_APP","He borrado el token, porque existía")
        }
    }
}