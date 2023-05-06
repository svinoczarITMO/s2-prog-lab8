package ru.itmo.se.prog.lab7.server.data

import kotlinx.serialization.Serializable

/**
 * Model of Location. Sub-model of the <code>Person</code>.
 *
 * @constructor x: Int, y: Long?, z: Int
 * @author svinoczar
 * @since 1.0.0
 */
@Serializable
class Location (var x: Int, var y: Long?, var z: Int) {

}