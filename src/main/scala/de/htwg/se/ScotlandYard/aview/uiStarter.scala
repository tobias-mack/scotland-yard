package de.htwg.se.ScotlandYard.aview

import de.htwg.se.ScotlandYard.aview
import de.htwg.se.ScotlandYard.controller.ControllerInterface
import javafx.embed.swing.JFXPanel

object uiStarter:
  def apply(ui: String, controller: ControllerInterface): Unit =
    ui match
      case "gui" =>
        new JFXPanel()
        aview.GUI.GUI(controller).run()
      case "tui" =>
        val tui = Tui(controller)
        println("Welcome to Scotland Yard:\n" +
          "1. Type in the player number: " + controller.numOfPlayers + "\n" +
          "2. Type in the player names\n" +
          "3. Type in transportation: taxi / bus / sub\n" +
          "!!! Player 1 is MisterX !!!\n")
        tui.runTui()
