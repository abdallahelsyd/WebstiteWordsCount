package com.instabug.lib

import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.URL

object Network {

    fun get(url: String): String {
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
    }

}