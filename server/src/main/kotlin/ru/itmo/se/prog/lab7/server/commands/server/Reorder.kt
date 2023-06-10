package ru.itmo.se.prog.lab7.server.commands.server


import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import ru.itmo.se.prog.lab7.common.data.Data
import ru.itmo.se.prog.lab7.common.data.types.*
import ru.itmo.se.prog.lab7.common.data.Person
import ru.itmo.se.prog.lab7.server.commands.Command
import ru.itmo.se.prog.lab7.server.utils.managers.DataBaseManager
import java.util.*

/**
 * Turns the collection around.
 *
 * @author svinoczar
 * @since 1.0.0
 */
class Reorder: Command(ArgType.NO_ARG, StatusType.USER, LocationType.SERVER), KoinComponent {
    val dbmanager: DataBaseManager by inject()

    override fun getName(): String {
        return "reorder"
    }

    override fun getDescription(): String {
        return " - сортирует коллекцию в порядке, обратном нынешнему\n"
    }
    override fun execute(data: Data): Data {
        var result: String? = ""
        val bufferVector: Vector<Person> = Vector()
        for (element in collectionManager.collection) {
            bufferVector.insertElementAt(element,0)
        }
        collectionManager.collection = bufferVector
        if (dbmanager.selectPersonQuery == dbmanager.connect().prepareStatement("select * from person order by id;")) {
            dbmanager.selectPersonQuery = dbmanager.connect().prepareStatement("select * from person order by id DESC;")
        } else {
            dbmanager.selectPersonQuery = dbmanager.connect().prepareStatement("select * from person order by id;")
        }
        result = (message.getMessage("reordered"))
        data.answerStr = result
        return data
    }
}