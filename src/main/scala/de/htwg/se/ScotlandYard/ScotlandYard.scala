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
    tui.gameStart()
    var input: String = ""
    var order = 0
    do{
      input = readLine()
      tui.processInputLine(input,order)
      order = (order +1)%controller.board.player.size
    }
    while(input != "q")
  }
}
