package ru.itmo.se.prog.lab7.server.commands.server

import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import ru.itmo.se.prog.lab7.common.data.Data
import ru.itmo.se.prog.lab7.common.data.types.*
import ru.itmo.se.prog.lab7.server.commands.Command
import ru.itmo.se.prog.lab7.server.utils.DataBaseManager

/**
 * Saves the collection in the file Collection.json.
 *
 * @author svinoczar
 * @since 1.0.0
 */
class Save: Command(ArgType.NO_ARG, StatusType.ADMIN, LocationType.SERVER), KoinComponent {
    private val pathToFile = System.getenv("SERVER_COLLECTION_VAR")
    private val dbmanager: DataBaseManager by inject()

    override fun getName(): String {
        return "save"
    }

    override fun getDescription(): String {
        return " - сохраняет коллекцию в файл\n"
    }

    /**
     * execute method. Save collection to file
     */
//    override fun execute(data: Data): String? {
//        var result: String? = ""
//        val collection = Vector<Person>()
//        collection.addAll(collectionManager.collection)
////        for (i in collectionManager.collection) { println(i) }
//        val list = collectionManager.collectionToList()
//        println("list = $list")
//        val jsonString = serializer.serializePerson(list)
//        println("jsonString - $jsonString")
//        write.toFile(jsonString, pathToFile)
//        write.toFile("asdads", pathToFile)
//        result = (message.getMessage("saved"))
//        return result
//    }

        override fun execute(data: Data): String? {
        var result: String? = ""
        collectionManager.collection.forEach {
            println("${data.token.login} \n")
            dbmanager.updatePerson(it.id, it.name, it.coordinates.x, it.coordinates.y,
                it.creationDate as java.sql.Date, it.height, it.weight, it.hairColor,
                it.nationality, it.location.x, it.location.y!!, it.location.z, data.token.id)
        }
        result = (message.getMessage("saved"))
        return result
    }
}