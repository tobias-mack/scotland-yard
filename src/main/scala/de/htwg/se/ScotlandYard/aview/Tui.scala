
package de.htwg.se.ScotlandYard.aview
import de.htwg.se.ScotlandYard.controller
import de.htwg.se.ScotlandYard.controller.Controller
import de.htwg.se.ScotlandYard.model.Board
import de.htwg.se.ScotlandYard.util.Observer

class Tui (controller: Controller) extends Observer{
  controller.add(this)
/*
  def processInputLine(input: String, board: Board): String = {
    input match {
      case "help" => " "
      case "moveTo1" => val board1 = board.player1.setCell(board.player1,board.cell1)
        "Player1 moved to cell 1"
      case "moveTo2" => board.player1.setCell(board.player1,board.cell2)
        "Player1 moved to cell 2"
      case "moveTo3" => board.mrX.setCell(board.player1,board.cell3)
        "Player1 moved to cell 3"
      case "XmoveTo2" => board.mrX.setCell(board.mrX,board.cell2)
        "MrX moved to cell 2"
      case "showPosition" => board.player1.cell.number.toString
    }
  }*/

  override def update(): Unit = ???
}

