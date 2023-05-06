package ru.itmo.se.prog.lab7.client.commands

import ru.itmo.se.prog.lab7.client.utils.validation.Data
import ru.itmo.se.prog.lab7.common.data.types.ArgType
import ru.itmo.se.prog.lab7.common.data.types.LocationType
import ru.itmo.se.prog.lab7.common.data.types.StatusType


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
        return getName() + " - очищает коллекцию\n"
    }

    override fun execute(data: Data): String? {
        val result = (message.getMessage("clear"))
        collectionManager.collection.clear()
        return result
    }
}