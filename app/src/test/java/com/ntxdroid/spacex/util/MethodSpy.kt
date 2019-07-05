package com.ntxdroid.spacex.util

import java.lang.reflect.Method
import java.lang.reflect.Modifier
import java.util.ArrayList

object MethodSpy {

    fun publicMethods(clazz: Class<*>): List<Method> {
        val results = ArrayList<Method>()
        val methods = clazz.declaredMethods

        for (method in methods) {
            if (!method.isSynthetic) {
                val modifiers = method.modifiers
                if (modifiers and Modifier.PUBLIC == Modifier.PUBLIC) {
                    results.add(method)
                }
            }
        }

        return results
    }

    fun publicMethodNames(clazz: Class<*>): List<String> {
        val results = ArrayList<String>()
        val methods = publicMethods(clazz)

        for (method in methods) results.add(method.name)

        return results
    }

    private fun modifierFromString(s: String): Int {
        var m = 0x0
        if ("public" == s)
            m = m or Modifier.PUBLIC
        else if ("protected" == s)
            m = m or Modifier.PROTECTED
        else if ("private" == s)
            m = m or Modifier.PRIVATE
        else if ("static" == s)
            m = m or Modifier.STATIC
        else if ("final" == s)
            m = m or Modifier.FINAL
        else if ("transient" == s)
            m = m or Modifier.TRANSIENT
        else if ("volatile" == s) m = m or Modifier.VOLATILE
        return m
    }
}
