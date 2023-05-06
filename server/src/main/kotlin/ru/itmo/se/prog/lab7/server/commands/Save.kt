package ru.itmo.se.prog.lab7.server.commands

import ru.itmo.se.prog.lab7.common.data.types.*
import ru.itmo.se.prog.lab7.common.data.Person
import ru.itmo.se.prog.lab7.server.utils.validation.Data
import java.util.*

/**
 * Saves the collection in the file Collection.json.
 *
 * @author svinoczar
 * @since 1.0.0
 */
class Save: Command(ArgType.NO_ARG, StatusType.ADMIN, LocationType.SERVER) {
    private val pathToFile = System.getenv("SERVER_COLLECTION_VAR")
    override fun getName(): String {
        return "save"
    }

    override fun getDescription(): String {
        return getName() + " - сохраняет коллекцию в файл\n"
    }

    /**
     * execute method. Save collection to file
     */
    override fun execute(data: Data): String? {
        var result: String? = ""
        val collection = Vector<Person>()
        collection.addAll(collectionManager.collection)
        val list = collectionManager.collectionToList()
        val jsonString = serializer.serializePerson(list)
        write.toFile(jsonString, pathToFile)
        result = (message.getMessage("saved"))
        return result
    }
}