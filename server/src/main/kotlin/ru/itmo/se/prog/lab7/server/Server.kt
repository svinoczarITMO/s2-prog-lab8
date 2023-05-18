package ru.itmo.se.prog.lab7.server

import org.koin.core.context.GlobalContext.startKoin
import ru.itmo.se.prog.lab7.server.di.serverKoinModule
import ru.itmo.se.prog.lab7.server.utils.Loader
import java.io.File

fun main(args: Array<String>) {
    startKoin {
        modules(serverKoinModule)
    }
    val historyFile = File("D:\\ITMO\\2nd-semester\\prog-labs\\s2-prog-lab7\\server\\src\\main\\kotlin\\ru\\itmo\\se\\prog\\lab7\\server\\data\\history.log")
    historyFile.writeText("")
    val loader = Loader()
    val server = ServerApp()
//    println("Server started on port")
    loader.load()
    while (true) {
        server.start()
    }
}