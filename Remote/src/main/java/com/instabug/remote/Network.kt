package com.instabug.remote

import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.URL
import kotlin.concurrent.thread

object Network {
    fun get(url: String, callback: (data: String) -> Unit) {
        thread {
            try {

                val url = URL(url)
                val connection = url.openConnection()
                var content = ""
                BufferedReader(InputStreamReader(connection.getInputStream())).use { inp ->
                    var line: String?
                    while (inp.readLine().also { line = it } != null) {
                        content += line
                    }
                }
                callback(content)
            } catch (e: Exception) {
                e.printStackTrace()
                callback("")
            }
        }
    }

}