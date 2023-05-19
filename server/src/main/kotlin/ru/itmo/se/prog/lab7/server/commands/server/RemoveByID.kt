package ru.itmo.se.prog.lab7.server.commands.server


import ru.itmo.se.prog.lab7.common.data.Data
import ru.itmo.se.prog.lab7.common.data.Messages
import ru.itmo.se.prog.lab7.common.data.types.*
import ru.itmo.se.prog.lab7.server.commands.Command

/**
 * Deletes the collection item with the entered identifier.
 *
 * @author svinoczar
 * @since 1.0.0
 */
class RemoveByID: Command(ArgType.ONE_ARG, StatusType.USER, LocationType.SERVER) {
    override fun getName(): String {
        return "remove_by_id"
    }

    override fun getDescription(): String {
        return " ${Messages.cyanText}--id${Messages.resetColor} - удаляет элемент из коллекции по его id\n"
    }

    override fun execute(data: Data): String? {
        var result: String? = ""
        var flag = false
        val id = data.oneArg.toInt()
        try {
            try {
                for (element in collectionManager.collection) {
                    if (element.id == id) {
                        collectionManager.collection.remove(element)
                        flag = true
                        break
                    }
                }
            } catch (e: ArrayIndexOutOfBoundsException) {
                result = e.message
                return result
            }

        if (flag) {
            result = (
                message.getMessage("by_id") +
                        id +
                        message.getMessage("removed")
            )
        } else if (!flag){
            result = (message.getMessage("invalid_id"))
        }
        } catch (e: ArrayIndexOutOfBoundsException) {
            result = (message.getMessage("InvalidArgument"))
            return result
        }
        return result
    }
}