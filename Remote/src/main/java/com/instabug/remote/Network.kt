package com.instabug.remote

import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.URL
import kotlin.concurrent.thread

object Network {
    fun get(url: String, callback: (data: String,error:Exception?) -> Unit) {
        thread {
            try {

                val baseUrl = URL(url)
                val connection = baseUrl.openConnection()
                var content = ""
                BufferedReader(InputStreamReader(connection.getInputStream())).use { inp ->
                    var line: String?
                    while (inp.readLine().also { line = it } != null) {
                        content += line
                    }
                }
                callback(content,null)
            } catch (e: Exception) {
                e.printStackTrace()
                callback("",e)
            }
        }
    }

}