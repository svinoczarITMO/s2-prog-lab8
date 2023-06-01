package ru.itmo.se.prog.lab7.client

import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import ru.itmo.se.prog.lab7.client.utils.io.PrinterManager
import ru.itmo.se.prog.lab7.client.utils.Serializer
import ru.itmo.se.prog.lab7.common.data.Messages
import java.io.*
import java.net.InetSocketAddress
import java.nio.channels.SocketChannel

class ClientApp (): KoinComponent {
    private val ip = "localhost"
    private val port = 8844
    val serializer = Serializer()
    val write = PrinterManager()
    var authorized = false
    val message: Messages by inject()

    fun connection(): SocketChannel {
        return try {
            val clientSocket = SocketChannel.open()
            clientSocket.socket().connect(InetSocketAddress(ip, port))
            clientSocket
        } catch (e: RuntimeException) {
            println("Ошибка подключения.")
            SocketChannel.open(InetSocketAddress(ip, port))
            throw e
        }
    }

    fun request(obj: String) {
        try {
            val clientSocket = connection()
            if (clientSocket.isConnected) {
                val output = PrintWriter(clientSocket.socket().getOutputStream())
                output.write(obj)
                output.flush()
                clientSocket.shutdownOutput()
                response(clientSocket)
            }
        } catch (e: Exception) {
            println("Ошибка запроса.")
        }
    }

    fun response(clientSocket: SocketChannel) {
        try {
            val input: InputStream = clientSocket.socket().getInputStream()
            val bufferedReader = BufferedReader(InputStreamReader(input))
            val response = bufferedReader.readLines()
            for (line in response) {
                if (line == message.getMessage("successful_login")) {
                    authorized = true
                }
                write.linesInConsole(line)
            }
            clientSocket.socket().close()
        } catch (e: Exception) {
            println("Ошибка при получении ответа от сервера.")
        }
    }

}