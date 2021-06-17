package de.htwg.se.ScotlandYard.aview.GUI

import de.htwg.se.ScotlandYard.aview
import de.htwg.se.ScotlandYard.controller.controllerBaseImpl.Controller

object guiStarter {
  def apply(ui: String, controller: Controller): Unit = {
    ui match {
      case "gui" => aview.GUI.GUI(controller).run()
    }
  }
}
