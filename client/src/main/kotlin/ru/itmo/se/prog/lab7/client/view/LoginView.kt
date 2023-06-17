package ru.itmo.se.prog.lab7.client.view

import ConnectDi
import javafx.beans.property.SimpleStringProperty
import javafx.geometry.Pos
import javafx.scene.control.PasswordField
import javafx.scene.control.TextField
import javafx.scene.text.FontWeight
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import ru.itmo.se.prog.lab7.client.app.MyApp
import ru.itmo.se.prog.lab7.client.utils.validation.GuiClientValidator
import tornadofx.*

class LoginView : View(){
    private var loginField: TextField by singleAssign()
    private var passwordField: PasswordField by singleAssign()
    private val guiClientValidator = GuiClientValidator()
    private val di = ConnectDi()
    private val resultText = SimpleStringProperty("")

    override val root = vbox {
        form {
            fieldset("Login") {
                field("Username (login)") {
                    loginField = textfield()
                }
                field("Password") {
                    passwordField = passwordfield()
                }
            }
            button("Login") {
                action {
                    val username = loginField.text
                    val password = passwordField.text
                    val commandName = "login"
                    val flag = "main"
                    val args = mutableMapOf<String, String>("login" to username, "password" to password)
                    val paramList = mutableListOf(commandName, args, flag)
                    val data = guiClientValidator.validate(paramList)
                    val dataStr = Json.encodeToString(data)
                    val result = di.clientApp.request(dataStr)
                    when (result) {
                        di.message.getMessage("successful_login") -> {
                            replaceWith<DataBaseView>()
                        }
                        di.message.getMessage("invalid_login1") -> {
                            resultText.set(MyApp.bundle.getString("invalid_login1"))
                        }
                        di.message.getMessage("invalid_password") -> {
                            resultText.set(MyApp.bundle.getString("invalid_password"))
                        }
                    }
                }
            }
        }

        label(resultText).style {
            setAlignment(Pos.TOP_CENTER)
//            padding = box(30.px, 20.px)
            fontSize = 14.px
            textFill = c("#de0206")
            fontWeight = FontWeight.BOLD
            fontFamily = "Arial"
            alignment = Pos.TOP_LEFT
        }
    }
}

