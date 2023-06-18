package ru.itmo.se.prog.lab7.client.view

import javafx.beans.property.SimpleStringProperty
import javafx.geometry.Pos
import javafx.scene.control.PasswordField
import javafx.scene.control.TextField
import javafx.scene.text.FontWeight
import ru.itmo.se.prog.lab7.client.app.MyApp
import ru.itmo.se.prog.lab7.client.utils.validation.GuiClientValidator
import tornadofx.*

class LoginView : View(){
    private val inputLogin = SimpleStringProperty()
    private val inputPassword = SimpleStringProperty()
    private var loginField: TextField by singleAssign()
    private var passwordField: PasswordField by singleAssign()
    private val guiClientValidator = GuiClientValidator()
    private val resultText = SimpleStringProperty("")

    override val root = vbox {
        form {
            fieldset(MyApp.bundle.getString("login")) {
                field(MyApp.bundle.getString("username")) {
                    loginField = textfield(inputLogin)
                }
                field(MyApp.bundle.getString("pass")) {
                    passwordField = passwordfield(inputPassword)
                }
            }
            button(MyApp.bundle.getString("login")) {
                action {
                    val username = loginField.text
                    val password = passwordField.text
                    val args = mutableMapOf<String, String>("login" to username, "password" to password)
                    val result = MyApp.executeServerCommand.run("login", args)
                    when (result.answerStr) {
                        MyApp.di.message.getMessage("successful_login") -> {
                            replaceWith<DataBaseView>()
                        }
                        MyApp.di.message.getMessage("invalid_login1") -> {
                            resultText.set(MyApp.bundle.getString("invalid_login1"))
                        }
                        MyApp.di.message.getMessage("invalid_password") -> {
                            resultText.set(MyApp.bundle.getString("invalid_password"))
                        }
                    }
                    MyApp.login.set(inputLogin.value)
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

