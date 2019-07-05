package com.ntxdroid.spacex.testextensions

import java.io.File

const val TEST_RESOURCES = "src/test/resources"

fun getPath(fileName: String, clazz: Any): String {
    val resourcesDirectory = File("src/test/resources")
    val file = File(resourcesDirectory, fileName)
    return file.absolutePath
}

fun fileExists(fileName: String, clazz: Any): Boolean {
    val uri = clazz.javaClass.getResource("/$fileName")?.toURI()
    val file = uri?.let {
        File(it)
    }

    return file?.exists() ?: false
}

fun getFile(fileName: String, clazz: Any): File? {
    val uri = clazz.javaClass.getResource("/$fileName")?.toURI()
    return uri?.let {
        File(it)
    }
}

private fun getPath(fileName: String): String {
    val file = File(TEST_RESOURCES, fileName)
    return file.absolutePath
}