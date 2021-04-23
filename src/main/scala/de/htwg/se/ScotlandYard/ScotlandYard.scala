package de.htwg.se.ScotlandYard

import de.htwg.se.ScotlandYard.aview.Tui
import de.htwg.se.ScotlandYard.model.{Board, Cell, Player, StringRepresentation}

object ScotlandYard {
  def main(args: Array[String]): Unit = {
/*    val student = Player("Player 1",1)
    println("Hello, " + student.name)
    println("Here you can see the board: ")
    StringRepresentation.printBoard()
    println()
    println("... and a ticket")
    StringRepresentation.printTicket()

    println(StringRepresentation.playerName("YourName"))
    println(StringRepresentation.misterX())*/

    val board = Board()
    val tui = new Tui
    val palyer = tui.processInputLine("moveTo2", board)
    println(palyer)

  }
}
