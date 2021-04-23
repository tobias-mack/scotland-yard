package de.htwg.se.ScotlandYard.model

abstract class Player( val name: String,
                       val cell: Cell,
                       val ticket: Ticket,
                       val typ: Int) {
   def setName(player: Player, name: String): Player
   def setCell(player: Player, cell: Cell): Player
   def setTicket(player: Player, ticket: Ticket): Player
}
