package de.htwg.se.ScotlandYard

import com.google.inject.{Guice, Injector}
import de.htwg.se.ScotlandYard.aview.api.GameAPI
import de.htwg.se.ScotlandYard.aview.uiStarter
import de.htwg.se.ScotlandYard.controller.ControllerInterface
import fileIOComponent.api.FileIOAPI

import scala.io.StdIn.readLine
import scala.util.{Failure, Success, Try}

object ScotlandYard:
  val injector: Injector = Guice.createInjector(new ScotlandYardModule)
  val controller: ControllerInterface = injector.getInstance(classOf[ControllerInterface])
  controller.notifyObservers()
  var ui: String = "tui" //type tui or gui

  // please use java 16, otherwise it might not run

  def main(args: Array[String]): Unit =

    Try(GameAPI(controller)) match
      case Success(v) => println("View Rest Server is running!")
      case Failure(v) => println(s"View Rest Server couldn't be started! ${v.getMessage}${v.getCause}")

    val input: String = readLine()
    println(input)
    uiStarter(ui,controller)
