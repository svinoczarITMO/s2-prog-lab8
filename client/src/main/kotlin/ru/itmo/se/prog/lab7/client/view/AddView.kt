package ru.itmo.se.prog.lab7.client.view

import javafx.beans.property.SimpleStringProperty
import javafx.geometry.Pos
import javafx.scene.paint.Color
import tornadofx.*
import ru.itmo.se.prog.lab7.client.app.MyApp
import ru.itmo.se.prog.lab7.common.data.*
import java.util.Date
import ru.itmo.se.prog.lab7.common.data.Color as HairColor

class AddView: View() {
    private var inputName = SimpleStringProperty()
    private var inputCoordinateX = SimpleStringProperty()
    private var inputCoordinateY = SimpleStringProperty()
    private var inputHeight = SimpleStringProperty()
    private var inputWeight = SimpleStringProperty()
    private var inputHairColor = SimpleStringProperty()
    private var inputCountry = SimpleStringProperty()
    private var inputLocationX = SimpleStringProperty()
    private var inputLocationY = SimpleStringProperty()
    private var inputLocationZ = SimpleStringProperty()
    private var answerText = SimpleStringProperty()


    override val root = form {
        setPrefSize(1000.0, 450.0)
        fieldset {
            field(MyApp.bundle.getString("name")) {
                textfield(inputName).useMaxWidth
                style {
                    fontFamily = "Arial"
                }
            }
            field(MyApp.bundle.getString("coordinate_x")) {
                textfield(inputCoordinateX).useMaxWidth
                style {
                    fontFamily = "Arial"
                }
            }
            field(MyApp.bundle.getString("coordinate_y")) {
                textfield(inputCoordinateY).useMaxWidth
                style {
                    fontFamily = "Arial"
                }
            }
            field(MyApp.bundle.getString("height")) {
                textfield(inputHeight).useMaxWidth
                style {
                    fontFamily = "Arial"
                }
            }
            field(MyApp.bundle.getString("weight")) {
                textfield(inputWeight).useMaxWidth
                style {
                    fontFamily = "Arial"
                }
            }
            field("${MyApp.bundle.getString("hair_color")}: ${HairColor.values().map { it.toString() }}") {
                textfield(inputHairColor).useMaxWidth
                style {
                    fontFamily = "Arial"
                }
            }
            field("${MyApp.bundle.getString("nationality")}: ${Country.values().map { it.toString() }}") {
                textfield(inputCountry).useMaxWidth
                style {
                    fontFamily = "Arial"
                }
            }
            field(MyApp.bundle.getString("location_x")) {
                textfield(inputLocationX).useMaxWidth
                style {
                    fontFamily = "Arial"
                }
            }
            field(MyApp.bundle.getString("location_y")) {
                textfield(inputLocationY).useMaxWidth
                style {
                    fontFamily = "Arial"
                }
            }
            field(MyApp.bundle.getString("location_z")) {
                textfield(inputLocationZ).useMaxWidth
                style {
                    fontFamily = "Arial"
                }
            }
        }.style {
            setMaxSize(750.0, 900.0)
            alignment = Pos.TOP_CENTER
        }
        hbox(50, Pos.BOTTOM_RIGHT) {
            button("Execute") {
                style {
                    setAlignment(Pos.TOP_CENTER)
                    textFill = Color.WHITE
                    backgroundColor += Color.BLACK
                    padding = box(10.px, 20.px)
                }
                action {
                    try {
                        MyApp.guiClientValidator.params = arrayListOf(
                            inputName.value,
                            inputCoordinateX.value,
                            inputCoordinateY.value,
                            inputHeight.value,
                            inputWeight.value,
                            inputHairColor.value,
                            inputCountry.value,
                            inputLocationX.value,
                            inputLocationY.value,
                            inputLocationZ.value
                        )
                        MyApp.executeServerCommand.run("add", mutableMapOf(), "gui")
                    } catch (e: Exception) {
                        answerText.set("Something wrong in add view")
                    }

                    inputName.value = ""
                    inputCoordinateX.value = ""
                    inputCoordinateY.value = ""
                    inputHeight.value = ""
                    inputWeight.value = ""
                    inputHairColor.value = ""
                    inputCountry.value = ""
                    inputLocationX.value = ""
                    inputLocationY.value = ""
                    inputLocationZ.value = ""
                }
            }
            label(answerText).style {
                setAlignment(Pos.TOP_CENTER)
                padding = box(30.px, 20.px)
            }
        }
    }

}