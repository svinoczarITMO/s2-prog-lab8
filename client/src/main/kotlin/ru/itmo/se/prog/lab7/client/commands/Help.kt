package ru.itmo.se.prog.lab7.client.commands


import ru.itmo.se.prog.lab7.client.utils.validation.Data
import ru.itmo.se.prog.lab7.common.data.types.ArgType
import ru.itmo.se.prog.lab7.common.data.types.LocationType
import ru.itmo.se.prog.lab7.common.data.types.StatusType

/**
 * Outputs all commands and their descriptions.
 *
 * @author svinoczar
 * @since 1.0.0
 */
class Help: Command(ArgType.NO_ARG, StatusType.USER, LocationType.CLIENT) {
    override fun getName(): String {
        return "help"
    }

    override fun getDescription(): String {
        return getName() + " - выводит справку по доступным командам\n"
    }

    override fun execute(data: Data): String? {
        var result: String? = ""
        result = (message.getMessage("="))
        val classes = commandManager.parsePackage("ru.itmo.se.prog.lab6.commands", "Command")
            .filter { klass -> !klass.simpleName.equals("FastAdd") && !klass.simpleName.equals("PrintCollection") && !klass.simpleName.equals("GetElement") }
        for (klass in classes) {
            try {
                val command = klass.getConstructor().newInstance() as Command
                result += (command.getDescription())
            } catch (e: Exception) {
                result = e.message
            }
        }
        result += (message.getMessage("="))
        return result
    }
}