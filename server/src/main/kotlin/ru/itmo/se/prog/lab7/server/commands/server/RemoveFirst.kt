package ru.itmo.se.prog.lab7.server.commands.server


import ru.itmo.se.prog.lab7.common.data.Data
import ru.itmo.se.prog.lab7.common.data.types.*
import ru.itmo.se.prog.lab7.server.commands.Command

/**
 * Deletes the first item in the collection.
 *
 * @author svinoczar
 * @since 1.0.0
 */
class RemoveFirst: Command(ArgType.NO_ARG, StatusType.ADMIN, LocationType.SERVER) {
    override fun getName(): String {
        return "remove_first"
    }

    override fun getDescription(): String {
        return " - удаляет первый элемент из коллекции\n"
    }
    override fun execute(data: Data): Data {
        var result: String? = ""
        collectionManager.collection.remove(collectionManager.collection.first())
        result = (
            message.getMessage("first_element") +
                    message.getMessage("removed"))
        data.answerStr = result
        return data
    }
}