package ru.itmo.se.prog.lab7.client.di

import org.koin.dsl.module
import ru.itmo.se.prog.lab7.client.utils.*
import ru.itmo.se.prog.lab7.common.data.Messages

/**
 * Koin Module with all needed objects.
 *
 * @author svinoczar
 * @since 1.0.0
 */
val notKoinModule = module {
    single {
        PrinterManager()
    }

    single {
        ReaderManager()
    }

    single {
        Messages()
    }

//    single {
//        Validator()
//    }

    single {
        CommandManager()
    }

    single {
        CollectionManager()
    }

    single {
        Serializer()
    }
}