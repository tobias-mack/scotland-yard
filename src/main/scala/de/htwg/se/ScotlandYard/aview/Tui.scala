package de.htwg.se.ScotlandYard.aview

import de.htwg.se.ScotlandYard.controller.ControllerInterface
import de.htwg.se.ScotlandYard.controller.controllerBaseImpl.Controller
import de.htwg.se.ScotlandYard.util.{Observer, State, UI}


case class Tui (controller: ControllerInterface) extends UI with Observer{

  controller.add(this)
  override def processInput(input: String): Unit = controller.exec(input)

  override def update(): Boolean = {
    println(controller)
    true
  }
}

