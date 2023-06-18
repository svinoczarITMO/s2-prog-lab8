package ru.itmo.se.prog.lab7.client.view

import javafx.collections.ObservableList
import javafx.geometry.Pos
import javafx.scene.paint.Color
import ru.itmo.se.prog.lab7.client.app.MyApp
import ru.itmo.se.prog.lab7.common.data.Person
import tornadofx.*

class DataBaseView() : View() {
    private val tableData: ObservableList<Person> = mutableListOf<Person>().asObservable()

    override val root = tabpane {
        println(tableData)
        tab(MyApp.bundle.getString("table")) {
            borderpane() {
                primaryStage.isResizable = true
                right {
                    scrollpane {
                        style {
                            fitToHeight = true
                            fitToWidth = true
                        }
                        tableview(tableData) {
                            setPrefSize(1500.0, 900.0)
                            style {
                            }
                            isEditable = false
                            column(MyApp.bundle.getString("id"), Person::id)
                            column(MyApp.bundle.getString("name"), Person::name)
                            column(MyApp.bundle.getString("height"), Person::height)
                            column(MyApp.bundle.getString("weight"), Person::weight)
                            column(MyApp.bundle.getString("coordinate_x"), Person::coordinates).cellFormat {
                                text = it.x.toString()
                            }
                            column(MyApp.bundle.getString("coordinate_y"), Person::coordinates).cellFormat {
                                text = it.y.toString()
                            }
                            column(MyApp.bundle.getString("hair_color"), Person::hairColor)
                            column(MyApp.bundle.getString("nationality"), Person::nationality)
                            column(MyApp.bundle.getString("location_x"), Person::location).cellFormat {
                                text = it.x.toString()
                            }
                            column(MyApp.bundle.getString("location_y"), Person::location).cellFormat {
                                text = it.y.toString()
                            }
                            column(MyApp.bundle.getString("location_z"), Person::location).cellFormat {
                                text = it.z.toString()
                            }
                            column(MyApp.bundle.getString("owner_id"), Person::ownerId)
                        }

                    }

                }
                left {
                    maxWidth = 240.0
                    vbox {
                        label() {
                            textProperty().bind(MyApp.login)
                        }.style {
                            setAlignment(Pos.TOP_CENTER)
                            fontFamily = "Arial"
                            fontSize = 20.px
                            padding = box(10.px, 20.px)
                        }
                        style {
                            setAlignment(Pos.TOP_LEFT)
                            padding = box(30.px, 20.px)
                            setAlignment(Pos.TOP_CENTER)
                        }
                        spacing = 10.0

                        button(MyApp.bundle.getString("help")) {
                            style {
                                textFill = Color.WHITE
                                backgroundColor += Color.BLACK
                                padding = box(10.px, 5.px)
                            }
                            minWidth = 100.0
                            minHeight = 50.0
                            action {
                                openInternalWindow(HelpView::class)
                            }
                        }

                        button(MyApp.bundle.getString("info")) {
                            style {
                                textFill = Color.WHITE
                                backgroundColor += Color.BLACK
                                padding = box(10.px, 5.px)
                            }
                            minWidth = 100.0
                            minHeight = 50.0
                            action {
                                openInternalWindow(InfoView::class)
                            }
                        }
                        button(MyApp.bundle.getString("show")) {
                            style {
                                textFill = Color.WHITE
                                backgroundColor += Color.BLACK
                            }
                            minWidth = 100.0
                            minHeight = 50.0
//                            action {
//                                openInternalWindow(ShowView::class)
//                            }
                            action {
                                tableData.asObservable().removeAll()
                                val result = MyApp.executeServerCommand.run("show", mutableMapOf())
                                tableData.setAll(result.collection)
                            }
                        }

                        button(MyApp.bundle.getString("add")) {
                            style {
                                textFill = Color.WHITE
                                backgroundColor += Color.BLACK
                                padding = box(10.px, 5.px)
                            }
                            minWidth = 100.0
                            minHeight = 50.0
                            action {
                                openInternalWindow(AddView::class)
                            }
                        }

                        button(MyApp.bundle.getString("update")) {
                            style {
                                textFill = Color.WHITE
                                backgroundColor += Color.BLACK
                                padding = box(10.px, 5.px)
                            }
                            minWidth = 100.0
                            minHeight = 50.0
                            action {
                                openInternalWindow(UpdateView::class)
                            }
                        }

                        button(MyApp.bundle.getString("clear")) {
                            style {
                                textFill = Color.WHITE
                                backgroundColor += Color.BLACK
                                padding = box(10.px, 5.px)
                            }
                            minWidth = 100.0
                            minHeight = 50.0
                            action {
                                MyApp.executeServerCommand.run("clear", mutableMapOf())
                            }
                        }

                        button(MyApp.bundle.getString("remove_by_id")) {
                            style {
                                textFill = Color.WHITE
                                backgroundColor += Color.BLACK
                                padding = box(10.px, 5.px)
                            }
                            minWidth = 100.0
                            minHeight = 50.0
                            action {
                                openInternalWindow(RemoveByIdView::class)
                            }
                        }

                        button(MyApp.bundle.getString("group_counting_by_nationality")) {
                            style {
                                textFill = Color.WHITE
                                backgroundColor += Color.BLACK
                                padding = box(10.px, 5.px)
                            }
                            minWidth = 100.0
                            minHeight = 50.0
                            action {
                                openInternalWindow(GroupCountingByNationalityView::class)
                            }
                        }

                        button(MyApp.bundle.getString("min_by_weight")) {
                            style {
                                textFill = Color.WHITE
                                backgroundColor += Color.BLACK
                                padding = box(10.px, 5.px)
                            }
                            minWidth = 100.0
                            minHeight = 70.0
                            action {
                                openInternalWindow(MinByWeightView::class)
                            }
                        }

                        button(MyApp.bundle.getString("count_by_hair_color")) {
                            style {
                                textFill = Color.WHITE
                                backgroundColor += Color.BLACK
                                padding = box(10.px, 5.px)
                            }
                            minWidth = 100.0
                            minHeight = 70.0
                            action {
                                openInternalWindow(CountByHairColorView::class)
                            }
                        }

                        button(MyApp.bundle.getString("history")) {
                            style {
                                textFill = Color.WHITE
                                backgroundColor += Color.BLACK
                                padding = box(10.px, 5.px)
                            }
                            minWidth = 100.0
                            minHeight = 50.0
                            action {
                                openInternalWindow(HistoryView::class)
                            }
                        }

                        button(MyApp.bundle.getString("log_out")) {
                            style {
                                textFill = Color.WHITE
                                backgroundColor += Color.BLACK
                                padding = box(10.px, 5.px)
                            }
                            minWidth = 100.0
                            minHeight = 50.0
                            action {
                                MyApp.executeServerCommand.run("logout", mutableMapOf())
                                replaceWith<MainView>()
                            }
                        }

                        button(MyApp.bundle.getString("exit")) {
                            style {
                                textFill = Color.WHITE
                                backgroundColor += Color.BLACK
                                padding = box(10.px, 5.px)
                            }
                            minWidth = 100.0
                            minHeight = 50.0
                            action {
                                MyApp.executeServerCommand.run("exit", mutableMapOf())
                            }
                        }
                    }
                }
                onRefresh()
            }
        }
    }
}