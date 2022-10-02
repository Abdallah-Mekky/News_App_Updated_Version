package com.task1.data

import com.google.gson.Gson

object Utils {

    fun <T> Any.convertToDTOClass(myClass:Class<T>):T{

        val jsonString = Gson().toJson(this)
        return Gson().fromJson(jsonString,myClass)
    }
}