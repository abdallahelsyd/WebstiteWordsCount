package com.instabug.remote

import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.Exception
import java.net.URL
object Network {
    fun get(url: String): String {
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
            return content
        }catch (e:Exception){
            e.printStackTrace()
            return ""
        }
    }

}