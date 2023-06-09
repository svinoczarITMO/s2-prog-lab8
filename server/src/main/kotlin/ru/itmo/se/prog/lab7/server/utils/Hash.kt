package ru.itmo.se.prog.lab7.server.utils

import java.math.BigInteger
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import java.time.LocalTime

class Hash {
    fun encryptPassword(input: String) : String {
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

    fun encryptLogin (input: String): String {
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

    fun checkEncryption (input: String): String {
        return try {
            val md = MessageDigest.getInstance("SHA-256")
            val messageDigest = md.digest((input + input.length.toString()).toByteArray())
            val no = BigInteger(1, messageDigest)
            val hashText = no.toString(16)
            hashText
        } catch (e: NoSuchAlgorithmException) {
            throw RuntimeException(e)
        }
    }

    fun generateSalt(login: String): String {
        return "${LocalTime.now()}" + "${login.length/(1_000_000)}"
    }
}