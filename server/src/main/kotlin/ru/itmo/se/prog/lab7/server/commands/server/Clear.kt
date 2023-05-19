package ru.itmo.se.prog.lab7.server.commands.server


import ru.itmo.se.prog.lab7.common.data.Data
import ru.itmo.se.prog.lab7.common.data.types.*
import ru.itmo.se.prog.lab7.server.commands.Command

/**
 * Clears the collection.
 *
 * @author svinoczar
 * @since 1.0.0
 */
class Clear: Command(ArgType.NO_ARG, StatusType.USER, LocationType.SERVER) {
    override fun getName(): String {
        return "clear"
    }

    override fun getDescription(): String {
        return " - очищает коллекцию\n"
    }

    override fun execute(data: Data): String? {
        val result = (message.getMessage("clear"))
        collectionManager.collection.clear()
        return result
    }
}