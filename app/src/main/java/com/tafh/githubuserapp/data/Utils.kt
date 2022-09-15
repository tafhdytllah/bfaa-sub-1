package com.tafh.githubuserapp.data

import android.content.Context
import java.io.IOException

class Utils {
    fun getJsonFromAssets(context: Context, fileName: String) =
        try {
            context.assets.open(fileName).bufferedReader().use {
                it.readText()
            }
        } catch (e: IOException) {
            e.printStackTrace()
            null
        }
}