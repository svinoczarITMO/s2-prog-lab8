package ru.itmo.se.prog.lab7.client.commands.server


import ru.itmo.se.prog.lab7.client.commands.Command
import ru.itmo.se.prog.lab7.client.utils.validation.Data
import ru.itmo.se.prog.lab7.common.data.types.*

/**
 * Outputs "id-name" pairs of items in the collection.
 *
 * @author svinoczar
 * @since 1.0.0
 */
class Show: Command(ArgType.NO_ARG, StatusType.USER, LocationType.SERVER) {
    override fun getName(): String {
        return "show"
    }

    override fun getDescription(): String {
        return getName() + " - выводит в стандартный поток вывода все элементы коллекции в строковом представлении\n"
    }

    override fun execute(data: Data): String? {
        var result: String? = ""
        if (collectionManager.collection.size > 1) {
            for (i in 0 until collectionManager.collection.size-1) {
                result += ("Id: ${collectionManager.collection.elementAt(i).id}, Name: ${collectionManager.collection.elementAt(i).name}\n")
            }
            result += ("Id: ${collectionManager.collection.elementAt(collectionManager.collection.size-1).id}, " +
                    "Name: ${collectionManager.collection.elementAt(collectionManager.collection.size-1).name}")
        } else if (collectionManager.collection.size == 1) {
            result = ("Id: ${collectionManager.collection.elementAt(collectionManager.collection.size-1).id}, " +
                    "Name: ${collectionManager.collection.elementAt(collectionManager.collection.size-1).name}")
        } else {
            result = (message.getMessage("clean_collection"))
        }
        return result
    }
}