package ru.itmo.se.prog.lab7.server.commands.admin


import ru.itmo.se.prog.lab7.common.data.Data
import ru.itmo.se.prog.lab7.common.data.types.*
import ru.itmo.se.prog.lab7.server.commands.Command

/**
 * Outputs all items in the collection as units.
 *
 * @author svinoczar
 * @since 1.0.0
 */
class PrintCollection: Command(ArgType.NO_ARG, StatusType.ADMIN, LocationType.SERVER) {
    override fun getName(): String {
        return "print"
    }

    override fun getDescription(): String {
        return " - выводит в терминал все объекты коллекции в развернутом виде\n"
    }
    override fun execute(data: Data): String? {
        val result = (collectionManager.collection.toString())
        return result
    }
}
