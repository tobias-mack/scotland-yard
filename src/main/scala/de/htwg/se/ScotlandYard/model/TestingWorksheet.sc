import de.htwg.se.ScotlandYard.model.{Board, Cell, Player, Ticket}

import scala.io.StdIn.readLine

val board = Board()
board.cell
board.player
val x = Vector(1,2,1,2)
val y = x.updated(0,100) .updated(1,123)                // Vector(100, 2, 1, 2)