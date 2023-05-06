package ru.itmo.se.prog.lab7.client.utils

/**
 * The basic interface for implementing a printer. You must implement it before applying printers in PrinterManager.
 *
 * @author svinoczar
 * @since 1.0.0
 */
interface Printer {
    /**
     * Prints message in console.
     *
     * @param message printed message.
     */
    fun inConsole (message: Any?)

    /**
     * Prints message line by line in console.
     *
     * @param message printed message.
     */
    fun linesInConsole (message: Any?)

    /**
     * Prints message in file.
     *
     * @param message printed message.
     * @param pathToFile path to writing file.
     */
    fun toFile (message: Any?, pathToFile: String)
}