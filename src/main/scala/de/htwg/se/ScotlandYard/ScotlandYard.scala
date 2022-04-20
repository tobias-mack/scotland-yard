package de.htwg.se.ScotlandYard

import com.google.inject.{Guice, Injector}
import controller.ScotlandYardModule
import de.htwg.se.ScotlandYard.aview.uiStarter
import controller.ControllerInterface

import scala.util.{Failure, Success, Try}

object ScotlandYard:
  val injector: Injector = Guice.createInjector(new ScotlandYardModule)
  val controller: ControllerInterface = injector.getInstance(classOf[ControllerInterface])
  controller.notifyObservers()
  var ui: String = "gui" //type tui or gui

  // please use java 16, otherwise it might not run

  def main(args: Array[String]): Unit =
    Try(uiStarter(ui, controller)) match
      case Success(_) => println("Thank you for playing. Bye.")
      case Failure(s) => println(s"Could not create UI - $s")