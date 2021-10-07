package com.example.my_cinema.retrofit

object RetrofitCommon {
    val BASE_URL = "https://www.signalmediacorp.com/"

    val retrofitService: ApiRetrofitInterface
        get() = RetrofitClient.getClient(BASE_URL).create(ApiRetrofitInterface::class.java)
}

object RetrofitCommonImage {
    val BASE_URL = "https://www.signalmediacorp.com/"

    val retrofitService: ApiRetrofitImage
        get() = RetrofitClient.getClient(BASE_URL).create(ApiRetrofitImage::class.java)
}