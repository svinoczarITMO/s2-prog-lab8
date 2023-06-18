package ru.itmo.se.prog.lab7.client.view

import javafx.beans.property.SimpleStringProperty
import javafx.geometry.Pos
import ru.itmo.se.prog.lab7.client.app.MyApp
import tornadofx.*

class MinByWeightView: View() {
    private val resultText= SimpleStringProperty("")

    override val root= vbox {
        setPrefSize(400.0, 65.0)
        val result = MyApp.executeServerCommand.run("min_by_weight", mutableMapOf())
        resultText.set(result.answerStr)
        label(resultText).style {
            setAlignment(Pos.TOP_CENTER)
            padding = box(10.px, 10.px)
            fontSize = 17.px
            textFill = c("#000000")
            fontFamily = "Arial"
        }
    }
}