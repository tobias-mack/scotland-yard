package de.htwg.se.ScotlandYard

import com.google.inject.{Guice, Injector}
import de.htwg.se.ScotlandYard.aview.uiStarter
import de.htwg.se.ScotlandYard.controller.ControllerInterface
import fileIOComponent.api.FileIOAPI

import scala.util.{Failure, Success, Try}

object ScotlandYard:
  val injector: Injector = Guice.createInjector(new ScotlandYardModule)
  val controller: ControllerInterface = injector.getInstance(classOf[ControllerInterface])
  controller.notifyObservers()
  var ui: String = "gui" //type tui or gui

  // please use java 16, otherwise it might not run

  def main(args: Array[String]): Unit =

    Try(FileIOAPI) match
      case Success(v) => println("Persistance Rest Server is running!")
      case Failure(v) => println("Persistance Server couldn't be started! " + v.getMessage + v.getCause)

    Try(uiStarter(ui, controller)) match
      case Success(v) => println("Thank you for playing. Bye.")
      case Failure(v) => println("Could not create UI - $v")
