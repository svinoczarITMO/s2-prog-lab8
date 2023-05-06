//package ru.itmo.se.prog.lab6.utils
//
//import ru.itmo.se.prog.lab6.commands.Command
//import ru.itmo.se.prog.lab7.server.data.Color
//import ru.itmo.se.prog.lab7.common.data.Messages
//import org.koin.core.component.KoinComponent
//import org.koin.core.component.inject
//import ru.itmo.se.prog.lab7.server.utils.validation.Data
//import java.util.*
//
///**
// * Validates and handles commands and their arguments before executing.
// *
// * @author svinoczar
// * @since 1.0.0
// */
//class Validator: KoinComponent{
//    private val collectionManager: CollectionManager by inject()
//    private val commandManager: CommandManager by inject()
//    private val message: Messages by inject()
//    private val write: PrinterManager by inject()
//    private val commandBuffer = LinkedList<String>()
//
//    /**
//     * Validates what is command's type and arguments and starts command.
//     *
//     * @param args unchecked raw arguments.
//     */
//    fun validate (args: Array<Any>): String {
//        val commandName = args[0] as String
//        val mapOfArgs = mutableMapOf<String, Any?>()
//        val arguments = args.slice(1 until args.size)
//        val noArgs = arrayOf(
//            "print", "fadd",
//            "help", "info", "show", "history",
//            "clear", "save", "exit", "remove_first",
//            "reorder", "min_by_weight", "group_counting_by_nationality"
//        )
//        val oneArg = arrayOf(
//            "remove_by_id", "get",
//            "count_by_hair_color", "execute_script", "change_collection"
//        )
//        val newObj = arrayOf(
//            "add"
//        )
//        val argAndObj = arrayOf(
//            "update"
//        )
//
//        // Проверка количества аргументов для комманды update.
//        if (commandName in argAndObj){
//            if (args[2] != "execute"){
//                write.linesInConsole(message.getMessage("InvalidArgument"))
//                return ""
//            }
//        }
//        // Проверка количества аргументов для комманд с 0 аргументов и для add.
//        if (commandName in noArgs || commandName in newObj){
//            if (args[1] != "main" && args[1] != "execute"){
//                write.linesInConsole(message.getMessage("InvalidArgument"))
//                return ""
//            }
//        }
//
//        val oneArgCommands = mapOf<String, String>(
//            "execute_script" to "path",
//            "remove_by_id" to "id",
//            "get" to "id",
//            "history" to "buffer",
//            "count_by_hair_color" to "color",
//            "change_collection" to "type"
//        )
//
//        if (commandBuffer.size == 7) {
//            commandBuffer.pop()
//            commandBuffer.add(commandName)
//        } else {
//            commandBuffer.add(commandName)
//        }
//
//        mapOfArgs["buffer"] = commandBuffer
//
//        try {
//            when (commandName) {
//                in noArgs -> {
//                    mapOfArgs["none"] = null
//                }
//                in oneArg -> {
//                    val name = oneArgCommands[commandName]
//                    mapOfArgs["$name"] = extraValidation(name!!, arguments)
//                    if (commandName == "execute_script"){
//                        if (arguments[1] == "main"){
//                            mapOfArgs["depth"] = 0
//                        } else {
//                            mapOfArgs["depth"] = arguments[3].toString().toInt()
//                        }
//                    }
//                }
//                in newObj -> {
//                    mapOfArgs["flag"] = arguments[0]
//                    mapOfArgs["path"] = if (arguments[0] != "main") arguments[1] else ""
//                }
//                in argAndObj -> {
//                    mapOfArgs["elementId"] = arguments[0].toString().toInt()
//                    mapOfArgs["flag"] = arguments[1]
//                    mapOfArgs["path"] = if (arguments[1] != "main") arguments[2] else ""
//                }
//            }
//        } catch (e: NumberFormatException) {
//            write.linesInConsole(message.getMessage("InvalidArgument"))
//            return ""
//        }
//
//        val command = commandManager.getCommand("ru.itmo.se.prog.lab6.commands", commandName, "Command")
//        val result = execute(command, mapOfArgs)
//        return result.toString()
//    }
//
//    /**
//     * Executes command with valid arguments.
//     *
//     * @param command - Command.
//     * @param args - arguments of the command.
//     */
//    fun execute (command: Command?, args: Map<String, Any?>): String {
//        val result: String
//        try {
//            result = (command?.execute(Data)).toString()
//        } catch (e: NullPointerException) {
//            return ""
//        }
//        return result
//    }
//
//    /**
//     * Validates keywords to data.
//     *
//     * @param name - validating keywords.
//     * @param arguments - list of all arguments of the command.
//     */
//    fun extraValidation (name: String, arguments: List<Any?>): Any {
//        when (name) {
//            "path" -> return arguments[0].toString()
//            "color" -> return Color.valueOf((arguments[0].toString()).uppercase())
//            "id" -> return arguments[0].toString().toInt()
//            "type" -> return arguments[0].toString()
//        }
//        return 0
//    }
//
//    /**
//     * Validates path to file.
//     *
//     * @param path - path to file.
//     * @return path to script file.
//     */
//    fun explorer (path: String?): String {
//        var pathToScriptFile = ""
//        if (path != null) {
//            pathToScriptFile = path
//        }
//        return pathToScriptFile
//    }
//}