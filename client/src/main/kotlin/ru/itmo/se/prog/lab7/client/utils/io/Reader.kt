package ru.itmo.se.prog.lab7.client.utils.io

/**
 * A basic interface for implementing a reader. You must implement it before applying readers in ReaderManager.
 *
 * @author svinoczar
 * @since 1.0.0
 */
interface Reader {
    /**
     * Reads message from console.
     */
    fun fromConsole(): String

    /**
     * Reads message from file.
     *
     * @param pathToFile path to reading file.
     */
    fun fromFile(pathToFile: String): List<String>
}