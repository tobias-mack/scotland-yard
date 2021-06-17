package de.htwg.se.ScotlandYard

import com.google.inject.{Guice, Injector}
import de.htwg.se.ScotlandYard.aview.GUI.GUI
import de.htwg.se.ScotlandYard.aview.Tui
import de.htwg.se.ScotlandYard.controller.ControllerInterface
import de.htwg.se.ScotlandYard.controller.controllerBaseImpl.Controller
import de.htwg.se.ScotlandYard.model.gameComponents.Board
import de.htwg.se.ScotlandYard.util.UI

import scala.util.{Failure, Success, Try}

object ScotlandYard {
  val injector: Injector = Guice.createInjector(new ScotlandYardModule)
  val controller: ControllerInterface = injector.getInstance(classOf[ControllerInterface])
  val tui: UI = Tui(controller)
  val gui: UI = GUI(controller)
  controller.notifyObservers()

  def main(args: Array[String]): Unit = {
    Try(aview.GUI.guiStarter("gui",controller)) match {
      case Success(_) => println("Thank you for playing. Bye.")
      case Failure(v) => println("Could not create GUI" + v.getMessage + v.getCause)
    }
  }
}