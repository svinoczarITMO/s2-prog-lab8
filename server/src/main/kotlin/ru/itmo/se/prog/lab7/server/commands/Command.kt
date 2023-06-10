package ru.itmo.se.prog.lab7.server.commands


import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import ru.itmo.se.prog.lab7.common.data.Data
import ru.itmo.se.prog.lab7.common.data.Messages
import ru.itmo.se.prog.lab7.common.data.types.ArgType
import ru.itmo.se.prog.lab7.common.data.types.LocationType
import ru.itmo.se.prog.lab7.common.data.types.StatusType
import ru.itmo.se.prog.lab7.server.utils.*
import ru.itmo.se.prog.lab7.server.utils.io.PrinterManager
import ru.itmo.se.prog.lab7.server.utils.io.ReaderManager
import ru.itmo.se.prog.lab7.server.utils.managers.CollectionManager
import ru.itmo.se.prog.lab7.server.utils.managers.CommandManager

/**
 * A basic interface for implementing commands. You must implement it before applying a command in the CommandManager.
 *
 * @author svinoczar
 * @since 1.0.0
 */
abstract class Command(
    val arg: ArgType,
    val status: StatusType,
    val location: LocationType
) : KoinComponent {

    val collectionManager: CollectionManager by inject()
    val commandManager: CommandManager by inject()
    val write: PrinterManager by inject()
    val read: ReaderManager by inject()
    val message: Messages by inject()
    val serializer: Serializer by inject()

    /**
     * Returns command's name.
     */
    abstract fun getName(): String

    /**
     * Returns command's description.
     */
    abstract fun getDescription(): String

    /**
     * Executes command with "arg" as arguments and "collectionManager" as Collection Manager.
     *
     * @param data map of command's arguments.
     */
    abstract fun execute(data: Data): Data
}

