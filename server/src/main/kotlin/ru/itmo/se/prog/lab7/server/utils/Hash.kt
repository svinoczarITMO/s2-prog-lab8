package ru.itmo.se.prog.lab7.server.utils

import java.math.BigInteger
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import java.time.LocalTime
import java.util.Date
import kotlin.math.pow
import kotlin.time.Duration.Companion.minutes

class Hash {
    fun encryptPassword(input: String): String {
        return try {
            val md = MessageDigest.getInstance("SHA-384")
            val messageDigest = md.digest(input.toByteArray())
            val no = BigInteger(1, messageDigest)
            var hashText = no.toString(32)
            while (hashText.length < 24) {
                hashText = "xI${hashText}Ix"
            }
            hashText
        } catch (e: NoSuchAlgorithmException) {
            throw RuntimeException(e)
        }
    }

    fun encryptLogin(input: String): String {
        return try {
            val md = MessageDigest.getInstance("SHA-384")
            val messageDigest = md.digest(input.toByteArray())
            val no = BigInteger(1, messageDigest)
            val hashText = no.toString(12)
            hashText
        } catch (e: NoSuchAlgorithmException) {
            throw RuntimeException(e)
        }
    }

    fun generateSalt(login: String): String {
        return "${LocalTime.now()}" + "${login.length/(1_000_000)}"
    }
}

fun main() {
    val h = Hash()
    println(h.generateSalt("K1L2F9FDS9CHN83Z"))
}