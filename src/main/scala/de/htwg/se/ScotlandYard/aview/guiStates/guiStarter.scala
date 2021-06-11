package de.htwg.se.ScotlandYard.aview.guiStates

import de.htwg.se.ScotlandYard.aview
import de.htwg.se.ScotlandYard.aview.GUI.GUI
import de.htwg.se.ScotlandYard.controller.ControllerInterface
import de.htwg.se.ScotlandYard.controller.controllerBaseImpl.Controller

object guiStarter {
  def apply(ui: String, controller: Controller): Unit = {
    ui match {
      case "gui" => aview.GUI.GUI(controller).run()
    }
  }
}
