package ru.itmo.se.prog.lab7.server.exceptions

/**
 * @exception [CommandException] used if the command is not detected
 *
 * @author svinoczar
 * @since 1.0.0
 */
class CommandException(message: String?) : Throwable(message)