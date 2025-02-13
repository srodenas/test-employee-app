package com.example.srodenas.test_employee_app.network

import retrofit2.Response

class ApiService {
     suspend fun loginService(log: RequestEmployee): Result<ResponseEmployee> {
         try{
             val response = InstanceRetrofit.retrofitService.login(log)
             if (response.isSuccessful){
                 response.body()?.let{
                     body -> return Result.success(body)
                 }?: return Result.failure(RuntimeException("Respuesta de usuario nulo"))
             }else
                 return Result.failure(RuntimeException ("Error en la llamada a la api"))
         }catch (e : Exception) {
             return Result.failure(e)
         }

    }


    suspend fun registerService (user: RequestEmployee ): Result<ResponseEmployee>{
        try{
            val response = InstanceRetrofit.retrofitService.register(user)
            if (response.isSuccessful){
                response.body()?.let{
                        body -> return Result.success(body)
                }?: return Result.failure(RuntimeException("Respuesta de usuario nulo"))
            }else
                return Result.failure(RuntimeException ("Error en la llamada a la api"))
        }catch (e : Exception) {
            return Result.failure(e)
        }
    }
}