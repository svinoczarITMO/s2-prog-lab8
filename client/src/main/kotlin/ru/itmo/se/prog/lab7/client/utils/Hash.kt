package ru.itmo.se.prog.lab7.client.utils

import java.math.BigInteger
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException

class Hash {
    fun encrypt(input: String): String {
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
}