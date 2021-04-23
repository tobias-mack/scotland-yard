
package de.htwg.se.ScotlandYard.aview
import de.htwg.se.ScotlandYard.model.{Board, Cell, Player}

import scala.io.StdIn.readLine


class Tui {

  def processInputLine(input: String, board: Board): Player = {
    input match {
      case "moveTo1" => board.player1.setCell(board.player1,board.cell1)
      case "moveTo2" => board.player1.setCell(board.player1,board.cell2)
      case "moveTo3" => board.player1.setCell(board.player1,board.cell3)
    }
  }

}

