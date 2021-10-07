package com.example.my_cinema

import java.io.File
import java.nio.charset.Charset

fun getFileLines(filename: String): List<String> {
    return File(filename).readLines(Charset.forName("UTF-8"))
}

fun updateArrayList(file_name: String, list: ArrayList<String>){
    list.clear()
    list.addAll(getFileLines(file_name))
}

fun writeFile(text: String, destFile: String) {
    val f = File(destFile)
    if (!f.exists()) {
        f.createNewFile()
    }

    f.writeText(text, Charset.defaultCharset())
}

fun appendFile(text: String, destFile: String) {
    val f = File(destFile)
    if (!f.exists()) {
        f.createNewFile()
    }
    f.appendText("\n" + text, Charset.defaultCharset())
}

fun writeListFile(list: ArrayList<String>, destFile: String)
{
    val f = File(destFile)
    if (!f.exists()) {
        f.createNewFile()
    }
    if (list.isNotEmpty()) {
        f.writeText(list[0], Charset.defaultCharset())
        for (i in 1..list.size-1)
        {
            appendFile(list[i], destFile)
        }
    }
}