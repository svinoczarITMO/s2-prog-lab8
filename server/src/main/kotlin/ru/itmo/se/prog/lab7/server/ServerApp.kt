package ru.itmo.se.prog.lab7.server

import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import ru.itmo.se.prog.lab7.common.data.*
import ru.itmo.se.prog.lab7.common.data.types.ArgType
import ru.itmo.se.prog.lab7.common.data.types.LocationType
import ru.itmo.se.prog.lab7.common.data.types.StatusType
import ru.itmo.se.prog.lab7.server.commands.server.Save
import ru.itmo.se.prog.lab7.server.utils.Serializer
import ru.itmo.se.prog.lab7.server.utils.ServerValidator
import ru.itmo.se.prog.lab7.server.utils.managers.CollectionManager
import ru.itmo.se.prog.lab7.server.utils.managers.DataBaseManager
import java.io.*
import java.net.InetSocketAddress
import java.nio.channels.ServerSocketChannel
import java.nio.channels.SocketChannel
import java.util.*
import java.util.concurrent.Executors
import java.util.concurrent.ForkJoinPool
import java.util.concurrent.LinkedBlockingQueue
import java.util.logging.FileHandler
import java.util.logging.Logger


class ServerApp: KoinComponent {
    private val ip = "localhost"
    private val port = 8844
    var fileHandler = FileHandler("logfile.log")
    private val logger = Logger.getLogger("logger")
    private val serializer = Serializer()
    private val serverValidator = ServerValidator()
    private val saveData = Data("save", "save",
        Person(0,"SAVE", Coordinates(1.4f, 8.8f), Date(),180, 68,
            Color.YELLOW, Country.VATICAN, Location(1,2,3), -1), "",
        User(0,"login", "password"),"main", ArgType.NO_ARG, StatusType.ADMIN, LocationType.SERVER, "")
    private val save = Save()
    private val dbmanager: DataBaseManager by inject()
    private val collectionManager: CollectionManager by inject()
    private val forkJoinPool = ForkJoinPool.commonPool()
    private val cachedThreadPool = Executors.newCachedThreadPool()
    private val blockingRequestQueue = LinkedBlockingQueue<Data>()
    private val blockingResponseQueue = LinkedBlockingQueue<Data>()

    init {
        logger.info("Запуск сервера...")
    }

    fun start (){
        logger.info("Ожидание подключения...")
        try {
            val serverSocket = ServerSocketChannel.open()
            serverSocket.bind((InetSocketAddress(ip, port)))
            while (serverSocket != null) {
                val clientSocket = serverSocket.accept()
                logger.info("Подключение к БД")
                forkJoinPool.submit() { request(clientSocket) }
                val process = Process(logger, blockingRequestQueue, blockingResponseQueue, serverValidator)
                cachedThreadPool.submit(process)
                forkJoinPool.submit() { response(clientSocket) }
            }
        } catch (e: Exception) {
            logger.severe("Ошибка подключения.")
        }
    }

    fun request (clientSocketChannel: SocketChannel) {
        logger.info("Ожидание запроса...")
        try {
            val inputJson: InputStream = clientSocketChannel.socket().getInputStream()
            val bufferedReader = BufferedReader(InputStreamReader(inputJson))
            val dataStr = bufferedReader.readLine()?.trim()!!
            val inputData: Data = serializer.deserializeData(dataStr)
            blockingRequestQueue.put(inputData)
        } catch (e: Exception) {
            logger.severe(e.message + "Ошибка получения запроса.")
        }
    }

    fun response (clientSocketChannel: SocketChannel) {
        logger.info("Отправка ответа...")
        try {
            val outputData = Json.encodeToString(blockingResponseQueue.take())
            val output = PrintWriter(clientSocketChannel.socket().getOutputStream())
            output.write(outputData)
            output.flush()
            clientSocketChannel.shutdownOutput()
            save.execute(saveData)
            } catch (e: Exception) {
            logger.severe("Ошибка отправки ответа.")
        }
    }

    companion object class Process (private var logger: Logger,
                   private var blockingRequestQueue: LinkedBlockingQueue<Data>,
                   private var blockingResponseQueue: LinkedBlockingQueue<Data>,
                   private var serverValidator: ServerValidator) : Runnable {

        override fun run () {
            process()
        }

        private fun process () {
            logger.info("Обработка запроса...")
            try {
                val inputData = blockingRequestQueue.take()
                blockingResponseQueue.put(
                    serverValidator.validate(inputData)!!
                )
            } catch (e: Exception) {
                logger.severe(e.message + "Ошибка обработки запроса.")
            }
        }
    }
}
