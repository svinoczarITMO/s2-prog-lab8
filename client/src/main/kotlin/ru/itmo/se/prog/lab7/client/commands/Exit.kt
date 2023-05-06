package ru.itmo.se.prog.lab7.client.commands


import ru.itmo.se.prog.lab7.client.utils.validation.Data
import ru.itmo.se.prog.lab7.common.data.types.*
import kotlin.system.exitProcess

/**
 * Terminates the programm.
 *
 * @author svinoczar
 * @since 1.0.0
 */

@Suppress("UNREACHABLE_CODE")
class Exit: Command(ArgType.NO_ARG, StatusType.USER, LocationType.CLIENT) {
    override fun getName(): String {
        return "exit"
    }

    override fun getDescription(): String {
        return getName() + " - завершает программу\n"
    }

    override fun execute(data: Data): String? {
        val result: String? = ""
        exitProcess(1)
        return result
    }
}