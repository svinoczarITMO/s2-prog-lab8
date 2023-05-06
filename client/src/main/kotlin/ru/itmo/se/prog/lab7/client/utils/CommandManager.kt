package ru.itmo.se.prog.lab7.client.utils

import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.reflections.Reflections
import org.reflections.scanners.SubTypesScanner
import ru.itmo.se.prog.lab7.client.commands.Command
import ru.itmo.se.prog.lab7.common.data.Messages
import ru.itmo.se.prog.lab7.common.exceptions.CommandException

/**
 * Manages commands.
 *
 * @author svinoczar
 * @since 1.0.0
 */
class CommandManager: KoinComponent {
        val write: PrinterManager by inject()
        val message: Messages by inject()

        /**
         * Parses specified package and filters classes by specified name of Interface.
         *
         * @param packageName name of parsed package.
         * @param commandInterfaceName name of Interface.
         * @return Set<Class<*>>
         */
        fun parsePackage(packageName: String, commandInterfaceName: String): Set<Class<*>> {
                val reflections = Reflections(packageName, SubTypesScanner(false))
                return reflections.getSubTypesOf(Object::class.java)
                        .filter { klass -> !klass.simpleName.equals(commandInterfaceName) }
                        .toSet()
        }

        /**
         * Returns command as Command from specified package by name.
         *
         * @param packageName name of package.
         * @param commandInterfaceName name of Interface.
         * @param commandName command's name.
         * @return Command?
         */
        fun getCommand(packageName: String, commandName: String, commandInterfaceName: String): Command? {
                val classes = parsePackage(packageName, commandInterfaceName)

                for (klass in classes) {
                        try {
                                val command = klass.getConstructor().newInstance() as Command

                                if (command.getName() == commandName) {
                                        return command
                                }
                        } catch (e: CommandException) {
                                write.linesInConsole(message.getMessage("InvalidCommand"))
                        }
                }

                return null
        }
}
