package ru.itmo.se.prog.lab7.client.app

import javafx.scene.Scene
import ru.itmo.se.prog.lab7.client.view.HelloWorld
import tornadofx.*
import tornadofx.FX.Companion.primaryStage


class MyApp: App(HelloWorld::class, Styles::class){
    override fun createPrimaryScene(view: UIComponent): Scene {
        val scene = Scene(view.root)
        primaryStage.width = 800.0
        primaryStage.height = 600.0
        return scene
    }
}