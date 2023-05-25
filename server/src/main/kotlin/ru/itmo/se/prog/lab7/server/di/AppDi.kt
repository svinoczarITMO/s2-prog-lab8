package ru.itmo.se.prog.lab7.server.di

import org.koin.dsl.module
import ru.itmo.se.prog.lab7.common.data.Messages
import ru.itmo.se.prog.lab7.server.ServerApp
import ru.itmo.se.prog.lab7.server.utils.*
import ru.itmo.se.prog.lab7.server.utils.io.PrinterManager
import ru.itmo.se.prog.lab7.server.utils.io.ReaderManager

/**
 * Koin Module with all needed objects.
 *
 * @author svinoczar
 * @since 1.0.0
 */
val serverKoinModule = module {
    single {
        PrinterManager()
    }

    single {
        ReaderManager()
    }

    single {
        Messages()
    }

    single {
        CommandManager()
    }

    single {
        CollectionManager()
    }

    single {
        ServerApp()
    }

    single {
        DataBaseManager()
    }
}