package de.htwg.se.ScotlandYard.model

case class Detective(override val name: String,
                     override val cell: Cell = Cell(),
                     override val ticket: Ticket = Ticket(),
                     override val typ: Int = 0
                    ) extends Player(name, cell, ticket, typ) {

  override def setName(player: Player, name1: String): Player = {
    player.asInstanceOf[Detective].copy(name = name1 )
  }
  override def setCell(player: Player, cell1: Cell): Player = {
    player.asInstanceOf[Detective].copy(cell = cell1)
  }
  override def setTicket(player: Player, ticket1: Ticket): Player = {
    player.asInstanceOf[Detective].copy(ticket = ticket1)
  }
/*  override def toString: String = {
    val ret = "Name: " + name + " position: " + cell.number + " Tickets: " + ticket.toString + "||||"
    ret
  }*/

}
