package ru.itmo.se.prog.lab7.client.commands

import ru.itmo.se.prog.lab7.client.utils.AddPersonFields
import ru.itmo.se.prog.lab7.client.utils.validation.Data
import ru.itmo.se.prog.lab7.common.data.types.ArgType
import ru.itmo.se.prog.lab7.common.data.types.LocationType
import ru.itmo.se.prog.lab7.common.data.types.StatusType

/**
 * Adds new element in the collection.
 *
 * @author svinoczar
 * @since 1.0.0
 */
class Add: Command(ArgType.OBJECT, StatusType.USER, LocationType.SERVER) {
    private val set = AddPersonFields()

    override fun getName(): String {
        return "add"
    }

    override fun getDescription(): String {
        return getName() + " - добавляет новый элемент в коллекцию\n"
    }

    override fun execute(data: Data): String? {
//        val flag = data.placeFlag
//        val id: Int = if (collectionManager.collection.isNotEmpty()) collectionManager.collection.maxOf { it.id } + 1 else 1
//        var params = arrayListOf("null parameter", "null parameter", "null parameter", "null parameter", "null parameter",
//                                         "null parameter", "null parameter", "null parameter", "null parameter", "null parameter")
        var result: String? = ""
//
//        if (flag != "main") {
//            params = parametersParser(path)
//        }
//
//        try {
//        val name = set.name(params[0], flag)
//
//        val coordinates = Coordinates(set.coordinateX(params[1], flag), set.coordinateY(params[2], flag))
//
//        val creationDate = Date()
//
//        val height = set.height(params[3], flag)
//
//        val weight = set.weight(params[4], flag)
//
//        val hairColor = set.hairColor(params[5], flag)
//
//        val nationality = set.nationality(params[6], flag)
//
//        val location = Location(set.locationX(params[7], flag), set.locationY(params[8], flag), set.locationZ(params[9], flag))
//
//        val personElement =
//            Person(id, name, coordinates, creationDate, height, weight, hairColor, nationality, location)
//        collectionManager.collection.add(personElement)
//        } catch (e: IndexOutOfBoundsException) {
//            result = (message.getMessage("not_enough_args"))
//            return result
//        }
//
        result = (message.getMessage("added"))
        return result
    }
//
//    /**
//     * Parses item parameters from the script file.
//     *
//     * @author svinoczar
//     * @since 1.0.0
//     */
//    private fun parametersParser (path: String): ArrayList<String> {
//        val params = arrayListOf<String>()
//        val strings = File(path).readStrings()
//        for (id in 0 until strings.size) {
//            if (strings[id] == "add") {
//                for (n in 1..10) {
//                    params.add(strings[id + n].lowercase().trim())
//                }
//            }
//        }
//        return params
//    }
}
