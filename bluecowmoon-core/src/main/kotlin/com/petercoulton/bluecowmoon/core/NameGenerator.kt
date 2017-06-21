package com.petercoulton.bluecowmoon.core

import java.io.BufferedReader
import java.io.File
import java.io.InputStream
import java.io.InputStreamReader
import java.util.*
import kotlin.streams.toList

typealias Word = String

private fun readResource(path: String): InputStream = ClassLoader.getSystemClassLoader().getResourceAsStream(path)

class NameGenerator(val words: List<Word>) {

    companion object Builder {
        fun fromFile(path: String): NameGenerator = fromInputStream(File(path).inputStream())
        fun fromFile(file: File): NameGenerator = fromInputStream(file.inputStream())
        fun fromResourceFile(path: String): NameGenerator = fromInputStream(readResource(path))

        fun fromInputStream(input: InputStream): NameGenerator {
            val wordsInput = BufferedReader(InputStreamReader(input))
            return NameGenerator(wordsInput.lines().map(String::toLowerCase).toList())
        }
    }

    fun randomWord(): Word  = words[Random().nextInt(words.size)]
    fun words(count: Int = 3): List<Word> = (1..count).map { _ -> randomWord() }.toList()
    fun capitalizedName(count: Int = 3): String = words(count).map(String::capitalize).joinToString("")
    fun hyphenatedName(count: Int = 3): String = words(count).joinToString("-")
}
