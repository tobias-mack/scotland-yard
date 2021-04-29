package de.htwg.se.ScotlandYard

import de.htwg.se.ScotlandYard.aview.Tui
import de.htwg.se.ScotlandYard.controller.Controller
import de.htwg.se.ScotlandYard.model.Board

import scala.io.StdIn.readLine

object ScotlandYard {
  val controller = new Controller(Board())
  val tui = new Tui(controller)
  controller.notifyObservers()

  def main(args: Array[String]): Unit = {
    var input: String = ""

    do{
      input = readLine()
      tui.processInputLine(input)
    }
    while(input != "q")
  }
}
