package ru.itmo.se.prog.lab7.client.utils

import java.io.File

/**
 * Manages readers.
 *
 * @author svinoczar
 * @since 1.0.0
 */
class ReaderManager: Reader {
    override fun fromConsole(): String {
        return readln()
    }

    override fun fromFile(pathToFile: String): List<String> {
        return File(pathToFile).readLines()
    }

}