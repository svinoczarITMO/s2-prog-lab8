package ru.itmo.se.prog.lab7.client.view

import javafx.beans.property.SimpleStringProperty
import javafx.geometry.Pos
import javafx.scene.paint.Color
import javafx.scene.text.FontWeight
import ru.itmo.se.prog.lab7.client.app.MyApp
import tornadofx.*

class CountByHairColorView: View() {
    private val resultText= SimpleStringProperty("")
    private val input = SimpleStringProperty("")

    override val root= form {
        fieldset {
            alignment = Pos.TOP_CENTER
            field("${MyApp.bundle.getString("enter_hair_color")} \nRED,\nBROWN,\nYELLOW" ) {
                textfield(input).useMaxWidth
                style {
                    setMaxWidth(580.0)
                    textFill = c("#000000")
                    fontFamily = "Arial"
                    fontSize = 15.px
                    setAlignment(Pos.TOP_CENTER)
                    padding = box(10.px, 20.px)
                }
            }
        }
        hbox(50, Pos.BOTTOM_RIGHT){
            button("Execute") {
                style {
                    setAlignment(Pos.TOP_CENTER)
                    textFill = Color.WHITE
                    backgroundColor += Color.BLACK
                    padding = box(10.px, 20.px)
                }
                action {
                    val result = MyApp.executeServerCommand.run("count_by_hair_color", mutableMapOf("oneArg" to input.value))
                    resultText.set(result.answerStr)
                    input.value=""
                }
            }
            label(resultText).style {
                setAlignment(Pos.TOP_CENTER)
                padding = box(30.px, 20.px)
                fontSize = 15.px
                fontWeight = FontWeight.BOLD
                fontFamily = "Arial"
            }
        }
    }
}

