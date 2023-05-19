package ru.itmo.se.prog.lab7.server.utils.io

import org.jetbrains.kotlin.konan.file.File
import ru.itmo.se.prog.lab7.server.utils.io.Printer

/**
 * Manages printers.
 *
 * @author svinoczar
 * @since 1.0.0
 */
class PrinterManager: Printer {
    override fun inConsole(message: Any?) {
        print(message)
    }

    override fun linesInConsole (message: Any?) {
        println(message)
    }

    override fun toFile(message: Any?, pathToFile: String) {
        when (message) {
            is String -> File(pathToFile).writeText(message)
            is Array<*> -> File(pathToFile).writeLines(message as Iterable<String>)
            is List<*> -> File(pathToFile).writeLines(message as Iterable<String>)
        }
    }
}