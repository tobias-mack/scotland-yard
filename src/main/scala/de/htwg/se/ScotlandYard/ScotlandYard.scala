package de.htwg.se.ScotlandYard

import de.htwg.se.ScotlandYard.aview.Tui
import de.htwg.se.ScotlandYard.controller.Controller
import de.htwg.se.ScotlandYard.model.Board
import de.htwg.se.ScotlandYard.util.UI

import scala.io.StdIn.readLine

object ScotlandYard {
  val controller = new Controller(Board())
  val tui: UI = new Tui(controller)
  controller.notifyObservers()

  def main(args: Array[String]): Unit = {
    var input: String = ""
    println("|==== Welcome to Scotland-Yard! ====|\n" +
            "|   How many players want to play?  |\n" +
            "|   Type a number between 2-5: ")
    while (true) {
      input = readLine()
      if (input == "q") {return}
      tui.processInput(input)
    }
  }
}
