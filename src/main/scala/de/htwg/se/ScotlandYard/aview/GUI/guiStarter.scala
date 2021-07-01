/*
package de.htwg.se.ScotlandYard.aview.GUI

import de.htwg.se.ScotlandYard.aview
import de.htwg.se.ScotlandYard.aview.Tui
import de.htwg.se.ScotlandYard.controller.ControllerInterface

object guiStarter {
  def apply(ui: String, controller: ControllerInterface): Unit = {
    ui match {
      case "gui" => aview.GUI.GUI(controller).run()
      case "tui" =>
        val tui = Tui(controller)
        println("Welcome to Scotland Yard:\n" +
          "1. Type in the player number (2-5)\n" +
          "2. Type in the player names\n" +
          "!!! Player 1 is MisterX !!!\n")
        tui.runTui()
    }
  }
}
*/