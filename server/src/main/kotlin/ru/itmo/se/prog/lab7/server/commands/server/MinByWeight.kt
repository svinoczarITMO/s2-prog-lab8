package ru.itmo.se.prog.lab7.server.commands.server


import ru.itmo.se.prog.lab7.common.data.Data
import ru.itmo.se.prog.lab7.common.data.types.*
import ru.itmo.se.prog.lab7.server.commands.Command

/**
 * Outputs the collection item with the minimum weight.
 *
 * @author svinoczar
 * @since 1.0.0
 */
class MinByWeight: Command(ArgType.NO_ARG, StatusType.USER, LocationType.SERVER) {
    override fun getName(): String {
        return "min_by_weight"
    }

    override fun getDescription(): String {
        return " - выводит любой объект из коллекции, значение поля weight которого является минимальным\n"
    }

    override fun execute(data: Data): Data {
        var result: String? = ""
        val vector = collectionManager.collection
        var minWeight: Long = Long.MAX_VALUE
        var minWeightId: String = "0"
        for (element in vector) {
            if (element.weight < minWeight) {
                minWeight = element.weight
                minWeightId = element.id.toString()
            }
        }
        result += (message.getMessage("min_weight_id") + minWeightId) + "\n"
        result += (message.getMessage("weight") + minWeight) + "\n"
        data.answerStr = result
        return data
    }
}