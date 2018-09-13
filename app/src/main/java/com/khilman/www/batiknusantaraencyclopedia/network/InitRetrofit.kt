package com.khilman.www.listmovierecyclerview.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class InitRetrofit {
    companion object {
        //localhost:8888/api_encyclopedia/discover.php=

        val BASE_URL = "http://ALAMAT_SERVER/ROOT_FOLDER/";
        // Contoh: http://192.168.100.4:8888/api_encyclopedia/
        val BASE_URL_IMAGE = "${BASE_URL}images/";
    }

    fun getInit(): Retrofit {
        return Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
    }

    fun getInstance(): ApiServices {
        return getInit().create(ApiServices::class.java)
    }
}