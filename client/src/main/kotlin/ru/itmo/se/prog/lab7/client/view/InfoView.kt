package ru.itmo.se.prog.lab7.client.view

import javafx.beans.property.SimpleStringProperty
import javafx.geometry.Pos
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import ru.itmo.se.prog.lab7.client.app.MyApp
import tornadofx.*
import tornadofx.Stylesheet.Companion.box
import tornadofx.Stylesheet.Companion.label

class InfoView : View() {
    private val resultText= SimpleStringProperty("")

    override val root= vbox {
        setPrefSize(600.0, 300.0)
        val result = MyApp.executeServerCommand.run("info", mutableMapOf())
        resultText.set(result)
        label(resultText).style {
            setAlignment(Pos.CENTER)
            padding = box(10.px, 6.px)
            fontSize = 15.px
            textFill = c("#000000")
            fontFamily = "Arial"
        }
    }
}