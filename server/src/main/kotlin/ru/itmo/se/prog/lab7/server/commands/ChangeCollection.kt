package ru.itmo.se.prog.lab7.server.commands


import ru.itmo.se.prog.lab7.common.data.types.*
import ru.itmo.se.prog.lab7.server.utils.validation.Data

/**
 * Changes the collection type.
 *
 * @author svinoczar
 * @since 1.0.0
 */
class ChangeCollection: Command(ArgType.ONE_ARG, StatusType.USER, LocationType.SERVER) {
    override fun getName(): String {
        return "change_collection"
    }

    override fun getDescription(): String {
        return getName() + " - изменяет тип коллекции.)\n"
    }

    override fun execute(data: Data): String? {
        val type = data.oneArg
        var result: String? = ""
        try {
            collectionManager.changeType(type.toString())
        } catch (e: NullPointerException) {
            result = (message.getMessage("NoSuchType"))
            result += printSupportedTypes()
            return result
        }
        result = message.getMessage("type_changed") + collectionManager.getType()
        return result
    }

    /**
     * Outputs the supported collection types.
     *
     * @author svinoczar
     * @since 1.0.0
     */
    private fun printSupportedTypes() : String? {
        var output = message.getMessage("supported_types")
        for (type in collectionManager.getSupportedCollectionTypes()) {
            output += type.key + "\n"
        }
        return output
    }
}