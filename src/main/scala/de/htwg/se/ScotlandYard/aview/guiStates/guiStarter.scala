package de.htwg.se.ScotlandYard.aview.guiStates

import de.htwg.se.ScotlandYard.aview.GUI
import de.htwg.se.ScotlandYard.controller.Controller

object guiStarter {
  def apply(ui: String, controller: Controller): Unit = {
    ui match {
      case "gui" => GUI(controller).run()
    }
  }
}
