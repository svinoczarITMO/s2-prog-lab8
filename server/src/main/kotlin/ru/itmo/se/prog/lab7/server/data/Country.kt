package ru.itmo.se.prog.lab7.server.data

import kotlinx.serialization.Serializable

/**
 * Model of Country. Sub-model of the <code>Person</code>.
 *
 * @author svinoczar
 * @since 1.0.0
 */
@Serializable
enum class Country {
    USA,
    CHINA,
    VATICAN,
    NORTH_KOREA,
    JAPAN;
}