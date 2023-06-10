package ru.itmo.se.prog.lab7.server.commands.server


import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import ru.itmo.se.prog.lab7.common.data.*
import ru.itmo.se.prog.lab7.common.data.types.*
import ru.itmo.se.prog.lab7.server.commands.Command
import ru.itmo.se.prog.lab7.server.utils.managers.DataBaseManager

/**
 * Outputs "id-name" pairs of items in the collection.
 *
 * @author svinoczar
 * @since 1.0.0
 */
class Show: Command(ArgType.NO_ARG, StatusType.USER, LocationType.SERVER), KoinComponent {
    private val dbmanager: DataBaseManager by inject()
    override fun getName(): String {
        return "show"
    }

    override fun getDescription(): String {
        return " - выводит в стандартный поток вывода все элементы коллекции в строковом представлении\n"
    }

    override fun execute(data: Data): Data {
        var result: String? = ""
        val id = data.user.id
        println("id: $id")

        if (collectionManager.collection.size > 1) {
            val persons = dbmanager.selectPersonQuery.executeQuery()
            while (persons.next()) {
                val personId = persons.getInt("id")
                println("pID: $personId")
                val personName = persons.getString("name")
                println("pName: $personName")
                val ownerId = persons.getInt("owner_id")
                println("oId: $ownerId")
                result += ("Id: $personId, Name: $personName, Owner: ${checkOwner(id, ownerId)}")
                if (personId != collectionManager.collection.last().id) {
                    result += "\n"
                }
            }
        } else {
            result = (message.getMessage("clean_collection"))
        }
        data.answerStr = result
        return data
    }

    private fun checkOwner (yourId: Int, ownerId: Int): String {
        println("yourId: $yourId \nownerId: $ownerId")
        return if (yourId == ownerId) {
            "You"
        } else {
            "Not you"
        }
    }
}