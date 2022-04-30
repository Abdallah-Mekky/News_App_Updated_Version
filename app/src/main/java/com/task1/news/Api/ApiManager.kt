package com.task1.news.Api

import android.util.Log
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor


class ApiManager {

    companion object {

        private var retrofit: Retrofit? = null

        private fun getInstance(): Retrofit {

            if (retrofit == null) {

                val logging = HttpLoggingInterceptor { message -> Log.e("Api", message) }
                logging.setLevel(HttpLoggingInterceptor.Level.BODY)
                val client = OkHttpClient.Builder()
                    .addInterceptor(logging)
                    .build()

                retrofit = Retrofit.Builder().client(client).baseUrl("https://newsapi.org/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()

            }

            return retrofit!!
        }


        public fun getApis(): WepServices {

            return getInstance().create(WepServices::class.java)
        }

    }
}