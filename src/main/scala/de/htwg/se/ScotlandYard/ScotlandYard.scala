package de.htwg.se.ScotlandYard

import de.htwg.se.ScotlandYard.aview.Tui
import de.htwg.se.ScotlandYard.model.{Board}
import scala.io.StdIn.readLine

object ScotlandYard {
  var board: Board = Board()
  val tui = new Tui
  def main(args: Array[String]): Unit = {
/*    var input: String = ""
    println("ScotlandYardTesting")
    do{
      input = readLine()
      println(tui.processInputLine(input, board))
      if(board.player1.cell.equals(board.mrX.cell)) {
        println("Detective wins!")
        input == "q"
      }
    }
    while (input != "q")*/
  }
}
