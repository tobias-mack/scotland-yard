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
    val possibleTransport = List(1,2,3,4)
    var input: String = ""
    var order = 0
    do{
      input = readLine()
      val x = tui.processInputLine(input)
      if(possibleTransport.contains(x)) {
        println("where to?")
        input = readLine()
        println(tui.inputMovePlayer(input, order, x))
        order = (order + 1) % controller.board.player.size
      }
    }
    while(input != "q")
  }
}
