package de.htwg.se.ScotlandYard

import com.google.inject.{Guice, Injector}
import de.htwg.se.ScotlandYard.controller.ControllerInterface
import scala.util.{Failure, Success, Try}

object ScotlandYard {
  val injector: Injector = Guice.createInjector(new ScotlandYardModule)
  val controller: ControllerInterface = injector.getInstance(classOf[ControllerInterface])
  controller.notifyObservers()

  def main(args: Array[String]): Unit = {
    Try(aview.GUI.guiStarter("gui",controller)) match {               //type tui or gui
      case Success(_) => println("Thank you for playing. Bye.")
      case Failure(v) => println("Could not create GUI" + v.getMessage + v.getCause)
    }
  }
}