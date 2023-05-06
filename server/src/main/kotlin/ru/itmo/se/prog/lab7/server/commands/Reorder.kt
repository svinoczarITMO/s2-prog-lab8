package ru.itmo.se.prog.lab7.server.commands


import ru.itmo.se.prog.lab7.common.data.types.*
import ru.itmo.se.prog.lab7.common.data.Person
import ru.itmo.se.prog.lab7.server.utils.validation.Data
import java.util.*

/**
 * Turns the collection around.
 *
 * @author svinoczar
 * @since 1.0.0
 */
class Reorder: Command(ArgType.NO_ARG, StatusType.USER, LocationType.SERVER) {
    override fun getName(): String {
        return "reorder"
    }

    override fun getDescription(): String {
        return getName() + " - сортирует коллекцию в порядке, обратном нынешнему\n"
    }
    override fun execute(data: Data): String? {
        var result: String? = ""
        val bufferVector: Vector<Person> = Vector()
        for (element in collectionManager.collection) {
            bufferVector.insertElementAt(element,0)
        }
        collectionManager.collection = bufferVector
        result = (message.getMessage("reordered"))
        return result
    }
}