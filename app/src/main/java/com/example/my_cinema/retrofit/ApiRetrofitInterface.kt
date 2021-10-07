package com.example.my_cinema.retrofit

import android.graphics.Bitmap
import com.example.my_cinema.model.AllContent
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.converter.gson.GsonConverterFactory

import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface ApiRetrofitInterface {
    @GET("api/main_page")
    fun getAllContent(): Call<AllContent>
}

interface ApiRetrofitImage {
    @GET("/b/c/{id}.jpg")



    //fun getImage(@Query("id") id:String ): Call<Bitmap>
    fun getImage( @Path("id") id: String): Call<ResponseBody>
}