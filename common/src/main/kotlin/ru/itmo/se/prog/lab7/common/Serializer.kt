package ru.itmo.se.prog.lab7.common


import kotlinx.serialization.KSerializer
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encodeToString
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.Json
import ru.itmo.se.prog.lab7.common.data.Data
import ru.itmo.se.prog.lab7.common.data.Person
import java.text.SimpleDateFormat
import java.util.*

/**
 * Basic class for serializing the data.
 *
 * @author svinoczar
 * @since 1.0.0
 */
class Serializer {
    private val json = Json { prettyPrint = true }

    /**
     * Serializes the list of Person objects.
     */
    fun serializePerson (whatToSerialize: List<Person>): String {
        return json.encodeToString(whatToSerialize)
    }

    /**
     * Deserializes the list of Person objects.
     */
    fun deserializePerson (whatToDeserialize: String): List<Person> {
        return json.decodeFromString<List<Person>>(whatToDeserialize)
    }

    /**
     *
     */
    fun serializeData (whatToSerialize: Data): String {
        return json.encodeToString(whatToSerialize)
    }

    /**
     *
     */
    fun deserializeData (whatToDeserialize: String): Data {
        return try {
            json.decodeFromString<Data>(whatToDeserialize)
        } catch (e: Exception) {
            println("Error deserializing data: ${e.message}")
            json.decodeFromString<Data>(whatToDeserialize)
        }
    }
}

object DateAsStringSerializer : KSerializer<Date> {
    private val dateFormat = SimpleDateFormat("EEE MMM dd HH:mm:ss zzzz yyyy", Locale.ENGLISH)

    override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor("Date", PrimitiveKind.STRING)

    override fun serialize(encoder: Encoder, value: Date) {
        encoder.encodeString(dateFormat.format(value))
    }

    override fun deserialize(decoder: Decoder): Date {
        return dateFormat.parse(decoder.decodeString())
    }
}
